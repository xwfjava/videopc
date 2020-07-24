package com.example.alioos.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.alioos.utils.ExecUtils;
import com.example.alioos.utils.HttpVideoUtil;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * date 2020/7/23
 */
//@Service
public class HuoShanService {

    private static String downloadFilePath= "E:\\tool\\example\\pc\\";
    private static String convertFilePath= "E:\\tool\\example\\pc1\\";
    private static String filterFilePath= "E:\\tool\\example\\pc2\\";
    private static String getVideoListUrl= "https://creator.huoshan.com/aweme/v1/creator/data/billboard_list/?billboard_type_list=11,12";
    private static String referer= "https://creator.huoshan.com/";
    private static String getVideoUrl= "https://api.huoshan.com/hotsoon/item/video/_reflow/?video_id=v0200cf10000brvf1j27u0r420emligg&line=0&app_id=0&vquality=normal&watermark=2&long_video=0&sf=5&ts=1595496181&item_id=";
    public static void downloadVideo(){
        //获取列表
        String getVideoListStr = HttpVideoUtil.doGet(getVideoListUrl,referer);
        JSONObject object = JSONObject.parseObject(getVideoListStr);
        JSONArray billboard_data = object.getJSONArray("billboard_data");
        JSONArray element_list = billboard_data.getJSONObject(0).getJSONArray("element_list");
        JSONArray element_list1 = billboard_data.getJSONObject(1).getJSONArray("element_list");
        element_list.addAll(element_list1);
//        for (int i=0;i<element_list.size();i++){
        for (int i=0;i<10;i++){
            JSONObject jsonObject = element_list.getJSONObject(i);
            String link = jsonObject.getString("link");
            String id = link.substring(link.lastIndexOf("=") + 1);
            String fileName = id + ".mp4";
            File file = new File(downloadFilePath+fileName);
            HttpVideoUtil.download(getVideoUrl+id, referer, file);
            //去除火山小视频水印
            ExecUtils.execFilter(downloadFilePath+fileName,filterFilePath+fileName);
        }
    }

    public static void convertVideo(){
        //获取列表
        File dir = new File(downloadFilePath);
        if(!dir.exists()){
            System.out.println("文件夹不存在");
        }
        File[] listFiles = dir.listFiles();
//        for (int i=0;i<element_list.size();i++){
        for (int i=0;i<listFiles.length;i++){
            String absolutePath = listFiles[i].getAbsolutePath();
            String fileName = listFiles[i].getName();
            //转换分辨率
            ExecUtils.convertFilter(absolutePath,convertFilePath+fileName);
        }
    }

    public static void filterVideo(){
        //获取列表
        File dir = new File(convertFilePath);
        if(!dir.exists()){
            System.out.println("文件夹不存在");
        }
        File[] listFiles = dir.listFiles();
//        for (int i=0;i<element_list.size();i++){
        for (int i=0;i<listFiles.length;i++){
            String absolutePath = listFiles[i].getAbsolutePath();
            String fileName = listFiles[i].getName();
            //去除火山小视频水印
            ExecUtils.execFilter(absolutePath,filterFilePath+fileName);
        }
    }

    public static void main(String[] args) {
//        downloadVideo();
        filterVideo();
    }


}
