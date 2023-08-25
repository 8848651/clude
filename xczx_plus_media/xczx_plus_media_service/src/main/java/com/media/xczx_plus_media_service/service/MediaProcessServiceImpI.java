package com.media.xczx_plus_media_service.service;


import com.media.xczx_plus_media_model.model.Dao.MediaFiles;
import com.media.xczx_plus_media_model.model.Dao.MediaProcess;
import com.media.xczx_plus_media_model.model.Dao.MediaProcessHistory;
import com.media.xczx_plus_media_service.mapper.MediaFilesMapper;
import com.media.xczx_plus_media_service.mapper.MediaProcessHistoryMapper;
import com.media.xczx_plus_media_service.mapper.MediaProcessMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaProcessServiceImpI implements MediaProcessService {

    @Autowired
    private MediaFilesMapper mediaFilesMapper;

    @Autowired
    private MediaProcessMapper mediaProcessMapper;

    @Autowired
    private MediaProcessHistoryMapper mediaProcessHistoryMapper;

    @Override
    public List<MediaProcess> SelectXxljob(int shardIndex, int shardTotal) {
        List<MediaProcess> mediaProcesses = mediaProcessMapper.SelectList(shardTotal, shardIndex);
        return mediaProcesses;
    }

    @Override
    public boolean UpdateLockXxljob(Long id) {
        int result = mediaProcessMapper.UpdateLocks(id);
        return result <= 0 ? false : true;
    }

    @Override
    public void FailXxljob(MediaProcess mediaProcess) {
        mediaProcess.setStatus("3");
        mediaProcess.setFailCount(mediaProcess.getFailCount() + 1);
        mediaProcessMapper.updateById(mediaProcess);
    }

    @Override
    public void SuccessXxljob(MediaProcess mediaProcess) {
        MediaFiles mediaFiles = mediaFilesMapper.selectById(mediaProcess.getFileId());
        BeanUtils.copyProperties(mediaProcess, mediaFiles);
        mediaFilesMapper.updateById(mediaFiles);
        MediaProcessHistory mediaProcessHistory = new MediaProcessHistory();
        BeanUtils.copyProperties(mediaProcess, mediaProcessHistory);
        mediaProcessMapper.deleteById(mediaProcess);
        mediaProcessHistoryMapper.insert(mediaProcessHistory);
    }

}
