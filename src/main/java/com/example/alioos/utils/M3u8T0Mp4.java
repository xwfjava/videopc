package com.example.alioos.utils;

import com.qiniu.util.StringUtils;

import java.io.*;

/**
 * date 2020/10/14
 */
public class M3u8T0Mp4 {

    private static String m3u8Url = "https://d.mhkuaibo.com/20200504/FlHYDPue/1000kb/hls/index.m3u8";
    private static String pathBrefore = "https://d.mhkuaibo.com";
    private static String savePath = "F:\\pc\\福山家\\";
    private static String savePath1 = "F:/pc/福山家";

    public static void main(String[] args) throws IOException {
        saveFile(m3u8Url);
    }

    public static void saveFile(String url) throws IOException {

        //保存位置
        File file = new File(savePath+"index.m3u8");
        File parentFile1 = file.getParentFile();
        if(!parentFile1.exists()){
            parentFile1.mkdirs();
        }

        HttpVideoUtil.download(url,"*",file);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        String readLine = reader.readLine();
        while (!StringUtils.isNullOrEmpty(readLine)){
            if(readLine.indexOf(".ts")!=-1){
                File ts = new File(savePath1 + readLine);
                File parentFile = ts.getParentFile();
                if(!parentFile.exists()){
                    parentFile.mkdirs();
                }
                HttpVideoUtil.download(pathBrefore+readLine, "*", ts);
            }
            readLine = reader.readLine();
        }
    }
}
