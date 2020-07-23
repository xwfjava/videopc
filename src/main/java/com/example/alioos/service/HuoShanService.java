package com.example.alioos.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.alioos.utils.HttpVideoUtil;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * date 2020/7/23
 */
//@Service
public class HuoShanService {

    private static String filePath= "E:\\tool\\example\\pc\\";
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
        for (int i=0;i<element_list.size();i++){
            JSONObject jsonObject = element_list.getJSONObject(i);
            String link = jsonObject.getString("link");
            String id = link.substring(link.lastIndexOf("=") + 1);
            File file = new File(filePath + id + ".mp4");
            HttpVideoUtil.download(getVideoUrl+id, referer, file);
        }
    }

    public static void main(String[] args) {
        downloadVideo();
    }
}
