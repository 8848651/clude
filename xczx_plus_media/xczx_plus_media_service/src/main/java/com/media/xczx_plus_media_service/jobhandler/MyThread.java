package com.media.xczx_plus_media_service.jobhandler;

import com.base.xczx_plus_base.base.utils.Mp4VideoUtil;
import com.media.xczx_plus_media_model.model.Dao.MediaProcess;
import com.media.xczx_plus_media_service.service.MediaProcessService;
import com.media.xczx_plus_media_service.service.MinionService;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;

import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

public class MyThread extends Thread {

    private MinionService minionService;
    private MediaProcessService mediaProcessService;
    private MediaProcess mediaProcess;
    private CountDownLatch countDownLatch;

    public MyThread(MinionService minionService
            ,MediaProcessService mediaProcessService
            ,MediaProcess mediaProcess
            , CountDownLatch countDownLatch) {
        this.mediaProcess=mediaProcess;
        this.mediaProcessService=mediaProcessService;
        this.minionService=minionService;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            //获取锁
            boolean lock= mediaProcessService.UpdateLockXxljob(mediaProcess.getId());
            if(!lock){
                mediaProcessService.FailXxljob(mediaProcess);
                return;
            }
            //下载文件
            File file=minionService.MinioDownload(mediaProcess.getBucket(), mediaProcess.getFilePath(),"minio",".temp");
            if(file==null){
                mediaProcessService.FailXxljob(mediaProcess);
                return;
            }
            //进行文件转码
            File mp4File = File.createTempFile("mp4", ".mp4");
            Mp4VideoUtil videoUtil = new Mp4VideoUtil("D:\\ffmpeg\\ffmpeg.exe", file.getAbsolutePath(), mp4File.getName(), mp4File.getAbsolutePath());
            String result = videoUtil.generateMp4();
            file.delete();
            if (!result.equals("success")) {
                mediaProcessService.FailXxljob(mediaProcess);
                return;
            }
            //上传minio并删除原视频
            ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(mp4File.getName());
            String mimeType = extensionMatch.getMimeType();
            String filePath = mediaProcess.getFilePath();
            String[] split1 = filePath.split("\\.");
            String[] split2 = mimeType.split("/");
            String filepath = split1[0] +"."+ split2[split2.length - 1];
            String[] split3 = mediaProcess.getFilename().split("\\.");
            String filename = split3[0]+"."+ split2[split2.length - 1];
            String Url="/"+mediaProcess.getBucket()+"/"+filepath;
            LocalDateTime finishDate = LocalDateTime.now();
            int FailCount = mediaProcess.getFailCount() + 1;
            Boolean aBoolean = minionService.MinionAdd(mediaProcess.getBucket(), filepath, mp4File.getAbsolutePath(), mimeType);
            Boolean bBoolean = minionService.MinionDelete(mediaProcess.getBucket(), filePath);
            mp4File.delete();
            if(!aBoolean||!bBoolean){
                mediaProcessService.FailXxljob(mediaProcess);
                return;
            }
            //进行更新数据库
            mediaProcess.setFilePath(filepath);
            mediaProcess.setStatus("2");
            mediaProcess.setFailCount(FailCount);
            mediaProcess.setFilename(filename);
            mediaProcess.setUrl(Url);
            mediaProcess.setFinishDate(finishDate);
            mediaProcessService.SuccessXxljob(mediaProcess);
        }catch (Exception e){
            mediaProcessService.FailXxljob(mediaProcess);
        }finally {
            //计数器-1
            countDownLatch.countDown();
        }
    }
}
