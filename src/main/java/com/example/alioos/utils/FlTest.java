package com.example.alioos.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * date 2020/8/12
 */
public class FlTest {
    public static void main1(String[] args) {
        String parentPath = "E:\\tool\\example\\fl\\";
        String flPath = "E:\\tool\\example\\fl1\\";
        String tsSuffix = "%03d.ts";
        File file = new File(parentPath);
        File[] files = file.listFiles();
        List<String> list = new ArrayList();
        Arrays.stream(files).forEach(item->{
            String filePath = item.getAbsolutePath();
            String destination = flPath+item.getName().replace(".mp4", ".m3u8");
            String ts = destination.replace(".m3u8", tsSuffix);
            ExecUtils.execSectioning2(filePath, destination,ts);
            list.add(destination);
        });

        list.forEach(System.out::println);
    }

    public static void main2(String[] args) {
        String imagePath = "E:\\tool\\example\\quanminImage\\";
        File file1 = new File(imagePath);
        File[] files1 = file1.listFiles();
        List<File> fileList = new ArrayList<>();
        Arrays.stream(files1).forEach(item->{
            fileList.addAll(Arrays.asList(item.listFiles()));
        });

        String flPath = "E:\\tool\\example\\quanminfl\\";
        String tsSuffix = "%03d.ts";
        List<String> list = new ArrayList();
        fileList.forEach(item->{
            String imgfilePath = item.getAbsolutePath();
            String filePath = imgfilePath.replace("quanminImage", "quanmin").replace(".jpg",".mp4");
            String destination = flPath+item.getName().replace(".jpg",".m3u8");
            String ts = destination.replace(".m3u8", tsSuffix);
            ExecUtils.execSectioning2(filePath, destination,ts);
            list.add(destination);
        });

        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
//        String videoPath = "E:\\tool\\example\\quanminfl\\";
//        File file1 = new File(videoPath);

        String imagePath = "E:\\tool\\example\\quanminImage2";
        File file1 = new File(imagePath);
        File[] files1 = file1.listFiles();
        List<File> fileList = new ArrayList<>();
        Arrays.stream(files1).forEach(item->{
            fileList.addAll(Arrays.asList(item.listFiles()));
        });

        int count = 0;
        for(File item : fileList){
            String key = item.getName();
            String absolutePath = item.getAbsolutePath();
            QiNiuYunUtil.add(absolutePath, key);
            System.out.println(++count);
        }
    }
}
