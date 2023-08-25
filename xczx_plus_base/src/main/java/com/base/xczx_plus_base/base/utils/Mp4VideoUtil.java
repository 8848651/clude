package com.base.xczx_plus_base.base.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mp4VideoUtil extends VideoUtil {

    String ffmpeg_path;
    String video_path;
    String mp4_name;
    String mp4folder_path;
    public Mp4VideoUtil(String ffmpeg_path, String video_path, String mp4_name, String mp4folder_path){
        super(ffmpeg_path);
        this.ffmpeg_path = ffmpeg_path;
        this.video_path = video_path;
        this.mp4_name = mp4_name;
        this.mp4folder_path = mp4folder_path;
    }
    private void clear_mp4(String mp4_path){
        File mp4File = new File(mp4_path);
        if(mp4File.exists() && mp4File.isFile()){
            mp4File.delete();
        }
    }
    public String generateMp4(){
        clear_mp4(mp4folder_path);
        List<String> commend = new ArrayList<String>();
        commend.add(ffmpeg_path);
        commend.add("-i");
        commend.add(video_path);
        commend.add("-c:v");
        commend.add("libx264");
        commend.add("-y");
        commend.add("-s");
        commend.add("1280x720");
        commend.add("-pix_fmt");
        commend.add("yuv420p");
        commend.add("-b:a");
        commend.add("63k");
        commend.add("-b:v");
        commend.add("753k");
        commend.add("-r");
        commend.add("18");
        commend.add(mp4folder_path  );
        String outstring = null;
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            outstring = waitFor(p);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Boolean check_video_time = this.check_video_time(video_path, mp4folder_path);
        if(!check_video_time){
            return outstring;
        }else{
            return "success";
        }
    }

    public static void main(String[] args) throws IOException {

        //ffmpeg的路径
        String ffmpeg_path = "D:\\ffmpeg\\ffmpeg.exe";//ffmpeg的安装位置
        //源avi视频的路径
        String video_path = "D:\\develop\\upload\\02-概述-分库分表是什么.avi";
        //转换后mp4文件的名称
        String mp4_name = "ddd.mp4";
        //转换后mp4文件的路径
        String mp4_path = "D:\\develop\\upload\\ddd.mp4";
        //创建工具类对象
        Mp4VideoUtil videoUtil = new Mp4VideoUtil(ffmpeg_path,video_path,mp4_name,mp4_path);
        String s = videoUtil.generateMp4();
    }
}