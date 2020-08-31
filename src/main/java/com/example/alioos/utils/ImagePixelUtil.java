package com.example.alioos.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * date 2020/8/14
 */
public class ImagePixelUtil {
    public static void main(String[] args) {
        String imagePath = "E:\\tool\\example\\quanminImage\\";
//        String imagePath2 = "E:\\tool\\example\\quanminImage2\\";
        File file1 = new File(imagePath);
        File[] files1 = file1.listFiles();
        List<File> fileList = new ArrayList<>();
        Arrays.stream(files1).forEach(item->{
            fileList.addAll(Arrays.asList(item.listFiles()));
        });

        fileList.forEach(item->{
            String oldPath = item.getAbsolutePath();
            String newPath = oldPath.replace("quanminImage", "quanminImage2");
            String parent = item.getParent();
            File parentFile = new File(parent.replace("quanminImage", "quanminImage2"));
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
            go(oldPath, newPath);
        });
    }

    private static void go(String oldPath, String newPath){
        int newWidth = 540;
        int newHeight = 960;
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(oldPath));

            //字节流转图片对象
            Image bi = ImageIO.read(in);
            //构建图片流
            BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            //绘制改变尺寸后的图
            tag.getGraphics().drawImage(bi, 0, 0, newWidth, newHeight, null);
            //输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(newPath));
            //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            //encoder.encode(tag);
            ImageIO.write(tag, "jpg", out);
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main1(String[] args) {
        String path1 = "E:\\tool\\example\\quanminImage\\dongzhiwu\\217847613072689613.jpg";
        String path2 = "E:\\tool\\example\\quanminImage2\\217847613072689613.jpg";

        go(path1, path2);
    }
}
