package com.media.xczx_plus_media_model.model.Dto;
import com.media.xczx_plus_media_model.model.Dao.MediaFiles;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReturnMediaFileDto extends MediaFiles {
    public ReturnMediaFileDto(MediaFiles mediaFiles){
        super.setId(mediaFiles.getId());
        super.setCompanyId(mediaFiles.getCompanyId());
        super.setCompanyName(mediaFiles.getCompanyName());
        super.setFilename(mediaFiles.getFilename());
        super.setFileType(mediaFiles.getFileType());
        super.setTags(mediaFiles.getTags());
        super.setBucket(mediaFiles.getBucket());
        super.setFilePath(mediaFiles.getFilePath());
        super.setFileId(mediaFiles.getFileId());
        super.setUrl(mediaFiles.getUrl());
        super.setUsername(mediaFiles.getUsername());
        super.setCreateDate(mediaFiles.getCreateDate());
        super.setChangeDate(mediaFiles.getChangeDate());
        super.setStatus(mediaFiles.getStatus());
        super.setRemark(mediaFiles.getRemark());
        super.setAuditStatus(mediaFiles.getAuditStatus());
        super.setAuditMind(mediaFiles.getAuditMind());
        super.setFileSize(mediaFiles.getFileSize());
    }
}
