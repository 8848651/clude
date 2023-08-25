package com.media.xczx_plus_media_service;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

class Test3 {

    private static MinioClient minioClient;
    static {
        minioClient =
                MinioClient.builder()
                        .endpoint("http://192.168.70.130:9000")
                        .credentials("minioadmin", "minioadmin")
                        .build();
    }


    @Test
    public void test0001()throws IOException{
        try {
            UploadObjectArgs aPublic = UploadObjectArgs.builder()
                    .bucket("video")
                    .object("2023/07/26/BBBB.mp3")
                    .filename("E:\\2024\\5.mp3")
                    .build();
            minioClient.uploadObject(aPublic);
            System.out.println("传输完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void TTT(){
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch("ABC.mp4");
        String mimeType = extensionMatch.getMimeType();
        String filePath = "2023/07/26/BBBB.mp3";
        String[] split2 = mimeType.split("/");
        String[] split1 = filePath.split("\\.");
        String filepath = split1[0] +"."+ split2[split2.length - 1];
        //  /2023/07/26/BBBB.mp4
        System.out.println(mimeType);
        System.out.println(filePath);
        System.out.println(filepath);
        try {
            UploadObjectArgs aPublicadd = UploadObjectArgs.builder()
                    .bucket("video")
                    .object(filepath)
                    .filename("E:\\2024\\1.mp4")
                    .contentType(mimeType)
                    .build();
            minioClient.uploadObject(aPublicadd);
            System.out.println("add");
        } catch (Exception e) {
            System.out.println("hellow world");
        }
    }

    public File downloadTempFile(String bucket, String FilePath){
        File minioFile = null;
        FileOutputStream outputStream = null;
        try{
            InputStream stream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(FilePath)
                    .build());
            minioFile=File.createTempFile("minio", ".mp4");
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

    public void DeleteFiles(String fileMd5, int chunkTotal) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        List<DeleteObject> list =new ArrayList<>();
        for (int i = 0; i < chunkTotal; i++) {
            DeleteObject deleteObject = new DeleteObject(fileMd5+"/" + i);
            list.add(deleteObject);
        }
        RemoveObjectsArgs removeObjectsArgs = RemoveObjectsArgs.builder()
                .bucket("video")
                .objects(list)
                .build();
        Iterable<Result<DeleteError>> results = minioClient
                .removeObjects(removeObjectsArgs);
        for (Result<DeleteError> result:results) {
            result.get();
        }
    }

    @Test
    void test1() throws IOException, ServerException, InsufficientDataException, InternalException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, XmlParserException, ErrorResponseException {
        File file1=new File("E:\\2024\\2000");
        File[] files = file1.listFiles();
        for (File file:files) {
            UploadObjectArgs aPublic = UploadObjectArgs.builder()
                    .bucket("test")
                    .object("video/"+file.getName())
                    .filename("E:\\2024\\2000\\"+file.getName())
                    .build();
            minioClient.uploadObject(aPublic);
        }
    }
    @Test
    void test2() throws IOException, ServerException, InsufficientDataException, InternalException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, XmlParserException, ErrorResponseException {

        List<ComposeSource> list=new ArrayList<>();
        File file1=new File("E:\\2024\\2000");
        File[] files = file1.listFiles();
        for (File file:files) {
            ComposeSource composeSource=ComposeSource.builder()
                    .bucket("test")
                    .object("video/"+file.getName())
                    .build();
            list.add(composeSource);
        }
        ComposeObjectArgs composeObjectArgs =ComposeObjectArgs.builder()
                .bucket("test")
                .object("video.mp4")
                .sources(list)
                .build();
        minioClient.composeObject(composeObjectArgs);

    }

    @Test
    public void test_removeObjects() throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        List<DeleteObject> list =new ArrayList<>();
        File file1=new File("E:\\2024\\2000");
        File[] files = file1.listFiles();
        for (File file:files) {
            DeleteObject deleteObject = new DeleteObject("video/" + file.getName());
            list.add(deleteObject);
        }
        RemoveObjectsArgs removeObjectsArgs = RemoveObjectsArgs.builder()
                .bucket("test")
                .objects(list)
                .build();
        Iterable<Result<DeleteError>> results = minioClient
                .removeObjects(removeObjectsArgs);
        for (Result<DeleteError> result:results) {
            DeleteError deleteError = result.get();
            System.out.println(deleteError);
        }
    }



}
