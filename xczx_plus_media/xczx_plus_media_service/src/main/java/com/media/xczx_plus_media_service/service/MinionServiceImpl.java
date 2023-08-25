package com.media.xczx_plus_media_service.service;


import io.minio.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MinionServiceImpl implements MinionService {

    @Autowired
    private MinioClient minioClient;


    @Override
    public Boolean MinioExist(String Bucket, String NewPath){
        try {
            InputStream stream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(Bucket)
                    .object(NewPath)
                    .build());
            if(stream!=null){
                stream.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean MinionAdd(String Bucket, String NewPath, String OldPath,String contentType){
        try {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Disposition", "inline; filename=\"" + NewPath + "\" ");
            UploadObjectArgs aPublic = UploadObjectArgs.builder()
                        .bucket(Bucket)
                        .object(NewPath)
                        .filename(OldPath)
                        .contentType(contentType)
                        .headers(headers)
                        .build();
            minioClient.uploadObject(aPublic);
        }catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public Boolean MinionDelete(String Bucket, String NewPath){
        try {
            RemoveObjectArgs aPublic = RemoveObjectArgs.builder()
                    .bucket(Bucket)
                    .object(NewPath)
                    .build();
            minioClient.removeObject(aPublic);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public boolean MinioMergeblock(String Bucket, String NewPath, String Oldlist, int chunkTotal){
        try {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Disposition", "inline; filename=\"" + NewPath + "\" ");
            List<ComposeSource> list=new ArrayList<>();
            for (int i = 0; i < chunkTotal; i++) {
                String minioName = Oldlist+ "/" +i;
                ComposeSource composeSource=ComposeSource.builder()
                        .bucket(Bucket)
                        .object(minioName)
                        .build();
                list.add(composeSource);
            }
            ComposeObjectArgs composeObjectArgs =ComposeObjectArgs.builder()
                    .bucket(Bucket)
                    .object(NewPath)
                    .sources(list)
                    .headers(headers)
                    .build();
            minioClient.composeObject(composeObjectArgs);
        }catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public Boolean MinioDeleteblock(String Bucket, String Oldlist, int chunkTotal) {
        try {
            List<DeleteObject> list =new ArrayList<>();
            for (int i = 0; i < chunkTotal; i++) {
                DeleteObject deleteObject = new DeleteObject(Oldlist+"/" + i);
                list.add(deleteObject);
            }
            RemoveObjectsArgs removeObjectsArgs = RemoveObjectsArgs.builder()
                    .bucket(Bucket)
                    .objects(list)
                    .build();
            Iterable<Result<DeleteError>> results = minioClient
                    .removeObjects(removeObjectsArgs);
            for (Result<DeleteError> result:results) {
                result.get();
            }
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public File MinioDownload(String bucket, String NewPath,String prefix,String suffix){
        File minioFile = null;
        FileOutputStream outputStream = null;
        try{
            InputStream stream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(NewPath)
                    .build());
            minioFile=File.createTempFile(prefix, suffix);
            outputStream = new FileOutputStream(minioFile);
            IOUtils.copy(stream,outputStream);
            stream.close();
            outputStream.close();
            return minioFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
