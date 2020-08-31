package com.example.alioos.utils;

import java.io.*;

/**
 * date 2020/8/17
 */
public class HeBinShiPin {


    //网络请求合并
    public static void main1(String[] args) {
        String tsFilePath = "E:\\tool\\example\\fl1\\";
        String m3u8FilePath = "E:\\tool\\example\\fl1\\669044995799036570.m3u8";
        String mp4FilePath = "E:\\tool\\example\\hb1\\669044995799036570.mp4";
        File m3u8File = new File(m3u8FilePath);
        if(!m3u8File.exists()){
            System.out.println("视频不存在");
        }
        try(Reader fileReader = new FileReader(m3u8File);
            BufferedReader reader = new BufferedReader(fileReader);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(mp4FilePath))){
            String readLine = reader.readLine();
            byte[] buffer = new byte[1024];
            int i;
            while (readLine!=null){
                if(readLine.indexOf(".ts")>0){
                    InputStream inputStream = HttpVideoUtil.doGetReturnInputStream(readLine);
                    while ((i = inputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, i);
                    }
                    inputStream.close();
                }
                readLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //本地文件合并
    public static void main(String[] args) {
        String tsFilePath = "E:\\tool\\example\\fl1\\";
        String m3u8FilePath = "E:\\tool\\example\\fl1\\669044995799036570.m3u8";
        String mp4FilePath = "E:\\tool\\example\\hb1\\669044995799036570.mp4";
        File m3u8File = new File(m3u8FilePath);
        if(!m3u8File.exists()){
            System.out.println("视频不存在");
        }
        try(Reader fileReader = new FileReader(m3u8File);
            BufferedReader reader = new BufferedReader(fileReader);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(mp4FilePath))){
            String readLine = reader.readLine();
            byte[] buffer = new byte[1024];
            int i;
            while (readLine!=null){
                if(readLine.indexOf(".ts")>0){
                    InputStream inputStream = HttpVideoUtil.doGetReturnInputStream(readLine);
                    while ((i = inputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, i);
                    }
                    inputStream.close();
                }
                readLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
