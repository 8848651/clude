package com.media.xczx_plus_media_service.jobhandler;
import com.media.xczx_plus_media_model.model.Dao.MediaProcess;
import com.media.xczx_plus_media_service.service.MediaProcessService;
import com.media.xczx_plus_media_service.service.MinionService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class VideoXxlJob {
    private static ThreadPoolExecutor pool;

    @Autowired
    private MinionService minionService;

    @Autowired
    private MediaProcessService mediaProcessService;


    static {
        pool = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    @XxlJob("VideoChange")
    public void VideoChange() throws InterruptedException {
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        List<MediaProcess> mediaProcesses = mediaProcessService.SelectXxljob(shardIndex, shardTotal);
        if(mediaProcesses==null){return;}
        CountDownLatch countDownLatch = new CountDownLatch(2);
        for (MediaProcess mediaProcess:mediaProcesses) {
            pool.execute(new MyThread(minionService,mediaProcessService,mediaProcess,countDownLatch));
        }
        //最大等待时间
        countDownLatch.await(1, TimeUnit.MINUTES);
    }
}
