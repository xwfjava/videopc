package com.example.alioos.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.alioos.config.QuanMinConstant;
import com.example.alioos.utils.ExecUtils;
import com.example.alioos.utils.HttpVideoUtil;

import java.io.File;

/**
 * date 2020/7/23
 */
//@Service
public class QuanMinService {

    public static String referer = "https://quanmin.baidu.com";

    public static void downloadVideo(){
        //获取列表
        String getVideoListStr = HttpVideoUtil.doGet(QuanMinConstant.hotVideoUrl,referer);
        JSONObject object = JSONObject.parseObject(getVideoListStr);
        JSONObject data1 = object.getJSONObject("apiData");
        JSONArray data2 = data1.getJSONArray("hot_video_list");
//        for (int i=0;i<element_list.size();i++){
        for (int i=0;i<data2.size();i++){
            JSONObject jsonObject = data2.getJSONObject(i);
            String link = jsonObject.getString("play_url");
            String id = jsonObject.getString("vid");;
            String fileName = id + ".mp4";
            File file = new File(QuanMinConstant.path+fileName);
            HttpVideoUtil.download(link, referer, file);
        }
    }

    public static void main(String[] args) {
//        downloadVideo();
        downloadVideo();
    }


}
