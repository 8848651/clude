package com.media.xczx_plus_media_service;

import com.alibaba.fastjson.JSON;
import com.base.xczx_plus_base.base.utils.Mp4VideoUtil;
import com.media.xczx_plus_media_model.model.Dao.MediaFiles;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

class Test1 {

    @Test
    void test1() throws Exception {
        File file2=new File("E:\\2024\\1.avi");
        FileInputStream inputStream=new FileInputStream(file2);
        File tempFile = File.createTempFile("minio", ".temp");
        FileOutputStream outputStream = new FileOutputStream(tempFile);
        IOUtils.copy(inputStream,outputStream);
        System.out.println(tempFile.getAbsolutePath());
    }

    @Test
    void tets001() throws IOException {
        File mp4File = File.createTempFile("mp4", ".mp4");
        Mp4VideoUtil videoUtil = new Mp4VideoUtil("D:\\ffmpeg\\ffmpeg.exe", "C:\\Users\\86151\\AppData\\Local\\Temp\\minio5084306165163011798.temp", mp4File.getName(), mp4File.getAbsolutePath());
        String result = videoUtil.generateMp4();
        System.out.println(mp4File.getAbsolutePath());
    }

    @Test
    void test2() throws IOException {
        String mimeType = "video/mp4";
        String filePath = "2023/07/25/ABC.avi";
        String[] split1 = filePath.split("\\.");
        String[] split2 = mimeType.split("/");
        String filepath = split1[0] +"."+ split2[split2.length - 1];
        System.out.println(filepath);
    }

    @Test
    void test3() throws Exception {
        File sourceFile = new File("E:\\2024\\3.mp4");
        String chunkFilePath = "E:\\2024\\2000\\";
        int chunkSize = 1024 * 1024 * 19;
        int chunkNum = (int) Math.ceil(sourceFile.length() * 1.0 / chunkSize);
        System.out.println(chunkNum);
        FileInputStream fileInputStream = new FileInputStream(sourceFile);
        for (int i = 0; i < chunkNum; i++) {
            File chunkFile = new File(chunkFilePath + i );
            FileOutputStream fileOutputStream = new FileOutputStream(chunkFile);
            IOUtils.copyLarge(fileInputStream ,fileOutputStream, 0, chunkSize);
            fileOutputStream.close();
        }
    }

    @Test
    void test4() throws JsonProcessingException {
        String json = "{\n" +
                "    \"id\": \"a39b96886277501c08b254679a1800d0\",\n" +
                "    \"companyId\": 1232141425,\n" +
                "    \"companyName\": null,\n" +
                "    \"filename\": \"5.pdf\",\n" +
                "    \"fileType\": \"application/pdf\",\n" +
                "    \"tags\": null,\n" +
                "    \"bucket\": \"file\",\n" +
                "    \"filePath\": \"2023/07/25/a39b96886277501c08b254679a1800d0.pdf\",\n" +
                "    \"fileId\": \"a39b96886277501c08b254679a1800d0\",\n" +
                "    \"url\": \"file/2023/07/25/a39b96886277501c08b254679a1800d0.pdf\",\n" +
                "    \"username\": null,\n" +
                "    \"createDate\": \"2023-07-25T17:11:23.440\",\n" +
                "    \"changeDate\": null,\n" +
                "    \"status\": 1,\n" +
                "    \"remark\": null,\n" +
                "    \"auditStatus\": \"002003\",\n" +
                "    \"auditMind\": null,\n" +
                "    \"fileSize\": 311479\n" +
                "}";

        MediaFiles mediaFiles = JSON.parseObject(json, MediaFiles.class);

        System.out.println(mediaFiles);
    }

    @Test
    void Test5() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String folder = sdf.format(new Date()).replace("-", "/")+"/";
        System.out.println(folder);
    }

    @Test
    void test001() throws Exception {
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(".avi");
        System.out.println(extensionMatch.getMimeType());
    }

    @Test
    void test002() throws Exception {
        String  aa="005/avi";
        String[] split = aa.split("/");
        System.out.println(Arrays.toString(split));
    }


}
