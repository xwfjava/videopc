package com.example.alioos.utils;

import com.example.alioos.config.QuanMinConstant;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * date 2020/8/13
 */
public class ImageMoveUtil {
    public static void main(String[] args) throws IOException {
        String imagePath = "E:\\tool\\example\\image\\";
        String videoPath = "E:\\tool\\example\\quanmin\\";
        String outputImagePath = "E:\\tool\\example\\quanminImage\\";
        File file = new File(imagePath);
        File[] files = file.listFiles();
        QuanMinConstant.TabName[] values = QuanMinConstant.TabName.values();
        for(File item : files){
            String name = item.getName().replace(".jpg",".mp4");
            for(QuanMinConstant.TabName tn : values){
                String name1 = tn.name();
                String path = videoPath+name1+"\\"+name;
                File file1 = new File(path);
                if(file1.exists()){
                    String parentOutputPathStr = outputImagePath+name1+"\\";
                    File file2 = new File(parentOutputPathStr);
                    if(!file2.exists()){
                        file2.mkdirs();
                    }
                    String outputPathStr = parentOutputPathStr + item.getName();
                    FileUtil.copyFile(item, outputPathStr);
                    break;
                }
            }
        }
    }
}
