package com.media.xczx_plus_media_service.service;

import java.io.File;

public interface MinionService {


    /**
     * 判断文件是否存在
     * @param Bucket 桶名
     * @param NewPath 文件minio path
     * @return
     */
    Boolean MinioExist(String Bucket,String NewPath);
    /**
     * 添加文件
     * @param Bucket 桶名
     * @param NewPath 文件minio path
     * @param OldPath 文件原始目录
     * @return
     */
    Boolean MinionAdd(String Bucket,String NewPath,String OldPath,String contentType);
    /**
     * 删除文件
     * @param Bucket 桶名
     * @param NewPath 文件minio path
     * @return
     */
    Boolean MinionDelete(String Bucket,String NewPath);
    /**
     * 合并分块
     * @param Bucket 桶名
     * @param NewPath 合并后文件minio path
     * @param Oldlist 分块所在目录
     * @param chunkTotal 分块数量
     * @return
     */
    boolean MinioMergeblock(String Bucket,String NewPath,String Oldlist,int chunkTotal);
    /**
     * 删除分块
     * @param Bucket 桶名
     * @param Oldlist 分块所在目录
     * @param chunkTotal 分块数量
     * @return
     */
    Boolean MinioDeleteblock(String Bucket,String Oldlist, int chunkTotal);

    /**
     * 文件下载
     * @param bucket 桶名
     * @param NewPath 文件minio path
     * @param prefix 临时文件前缀
     * @param suffix 临时文件后缀
     * @return
     */
    File MinioDownload(String bucket, String NewPath, String prefix, String suffix);
}
