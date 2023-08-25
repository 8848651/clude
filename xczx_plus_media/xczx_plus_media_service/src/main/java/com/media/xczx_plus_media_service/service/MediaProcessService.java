package com.media.xczx_plus_media_service.service;

import com.media.xczx_plus_media_model.model.Dao.MediaProcess;

import java.util.List;

public interface MediaProcessService {

    //分片获取任务
    List<MediaProcess> SelectXxljob(int shardIndex, int shardTotal);

    //获取锁
    boolean UpdateLockXxljob(Long id);

    //失败更新MediaProcess
    void FailXxljob(MediaProcess mediaProcess);

    //成功更新MediaFiles 删除MediaProcess  插入MediaProcessHistory
    void SuccessXxljob(MediaProcess mediaProcess);
}
