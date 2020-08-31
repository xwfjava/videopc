package com.example.alioos.utils;

import com.alibaba.fastjson.JSONArray;
import com.example.alioos.dao.QiNiuUtil;

import java.io.File;
import java.util.Arrays;

/**
 * date 2020/8/28
 */
public class CoverMapUtil {

    public static void main2(String[] args) {

        String videoRootPath = "E:\\tool\\example\\quanmin\\";

        File videoRootFile = new File(videoRootPath);
        File[] listFiles = videoRootFile.listFiles();

        Arrays.stream(listFiles).forEach(CoverMapUtil::cover);
    }

    private static void cover(File dir){

        File[] listFiles = dir.listFiles();
        Arrays.stream(listFiles).forEach(CoverMapUtil::copy);
    }

    private static void copy(File videoFile){
        String videoPath = videoFile.getAbsolutePath();
        String coverPath = videoPath.replace("quanmin","quanminImage2").replace(".mp4",".jpg");
        System.out.println(videoPath+"-----::::      "+coverPath);
        ExecUtils.getCoverMap(videoPath, coverPath);
    }


    public static void main1(String[] args) {
        String videoFilePath = "E:\\tool\\example\\quanmin\\more\\5286377509769433802.mp4";
        File file = new File(videoFilePath);
        copy(file);
    }

    static int i = 0;

    public static void main(String[] args) {
        String imgRootPath = "E:\\tool\\example\\quanminImage2\\";

        File imgRootFile = new File(imgRootPath);
        File[] listFiles = imgRootFile.listFiles();

        Arrays.stream(listFiles).forEach(item->{
            File[] dirFiles = item.listFiles();
            Arrays.stream(dirFiles).forEach(item1->{
                String fileName = item1.getName();
                String absolutePath = item1.getAbsolutePath();
                //查询数据库中是否存在该封面，存在上传，不存在不上传
                int countByCover = QiNiuUtil.countByCover(fileName);
                if(countByCover>0){
                    //上传封面
                    System.out.println(++i+"   "+fileName);
//                    if(i>=1381){
                        QiNiuYunUtil.add(absolutePath, fileName);
//                    }
                }
            });
        });
    }
}
