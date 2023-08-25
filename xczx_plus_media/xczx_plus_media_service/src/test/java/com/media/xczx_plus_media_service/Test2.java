package com.media.xczx_plus_media_service;
import com.media.xczx_plus_media_model.model.Dao.MediaProcess;
import com.media.xczx_plus_media_service.mapper.MediaProcessMapper;
import com.media.xczx_plus_media_service.service.MediaFilesService;
import com.media.xczx_plus_media_service.service.MediaFilesServiceImpl;
import com.media.xczx_plus_media_service.service.MediaProcessService;
import com.media.xczx_plus_media_service.service.MinionService;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import io.minio.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.io.*;
import java.util.List;

@SpringBootTest
public class Test2 {
    @Autowired
    MediaFilesServiceImpl  mediaFilesServiceImpl;
    @Autowired
    MediaFilesService mediaFilesService;
    @Autowired
    MinioClient minioClient;
    @Autowired
    private MinionService minionService;
    @Autowired
    private MediaProcessService mediaProcessService;
    @Autowired
    MediaProcessMapper mediaProcessMapper;
    @Value("${minio.bucket.video}")
    private String video;

    @Value("${minio.bucket.files}")
    private String file;

    @Test
    void test003() throws Exception {
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(".mp4");
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }


    @Test
    void test001() throws Exception {
       File file=minionService.MinioDownload("video","2023/07/28/359e4255cb8aab083d1c9baa5651d757.avi","minio",".temp");
        System.out.println(file.getAbsolutePath());
    }

    @Test
    void MinioMergeblock(){
        List<MediaProcess> mediaProcesses = mediaProcessService.SelectXxljob(1, 2);
        if(mediaProcesses==null){return;}
        for (MediaProcess mediaProcess:mediaProcesses) {
            System.out.println(mediaProcess.getId());
        }
    }

    @Test
    void MinioMergeblock1(){
        MediaProcess mediaProcess = mediaProcessMapper.selectById("1");
        System.out.println(mediaProcess);
        mediaProcessService.FailXxljob(mediaProcess);
    }

    /**
     * 添加
     */
    @Test
    public void test1()throws IOException{
        try {
            UploadObjectArgs aPublic = UploadObjectArgs.builder()
                    .bucket(video)
                    .object("1.mp4")
                    .filename("E:\\2024\\1.mp4")
                    .build();
            minioClient.uploadObject(aPublic);
            System.out.println("传输完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     */
    @Test
    public void test2()throws IOException{
        try {
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("public").build());
            RemoveObjectArgs aPublic = RemoveObjectArgs.builder()
                    .bucket("public")
                    .object("1.mp4")
                    .build();
            minioClient.removeObject(aPublic);
            System.out.println("删除完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
