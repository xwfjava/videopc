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

    public static void getVideo(){
        QuanMinConstant.TabName[] values = QuanMinConstant.TabName.values();
        for (int k=0;k<values.length;k++){
            String typeName = values[k].name();
            String parentPath = QuanMinConstant.path+typeName+"\\";
            File parentfile = new File(parentPath);
            if(!parentfile.exists()){
                parentfile.mkdirs();
            }
            //热门视频下载
            getHot(typeName, parentPath);
            //非热门视频下载
            for (int h=1;h<21;h++){
                //替换下载路径
                String url = String.format(QuanMinConstant.typeVideoUrl, h, typeName);
                System.out.println(url);
                //获取列表
                String getVideoListStr = HttpVideoUtil.doGet(url,referer);
                JSONObject object = JSONObject.parseObject(getVideoListStr);
                JSONObject data1 = object.getJSONObject("data");
                JSONObject data3 = data1.getJSONObject("list");
                //热门下载
                JSONArray data2 = data3.getJSONArray("video_list");
                for (int i=0;i<data2.size();i++){
                    JSONObject jsonObject = data2.getJSONObject(i);
                    String link = jsonObject.getString("play_url");
                    String id = jsonObject.getString("vid");;
                    String fileName = id + ".mp4";
                    File file = new File(parentPath+fileName);
                    HttpVideoUtil.download(link, referer, file);
                }
            }
        }
    }

    //热门下载
    private static void getHot(String typeName, String parentPath){
        //替换下载路径
        String url = String.format(QuanMinConstant.typeHotVideoUrl, typeName);
        //获取列表
        String getVideoListStr = HttpVideoUtil.doGet(url,referer);
        JSONObject object = JSONObject.parseObject(getVideoListStr);
        JSONObject data1 = object.getJSONObject("apiData");
        //热门下载
        JSONArray data2 = data1.getJSONArray("hot_video_list");
        for (int i=0;i<data2.size();i++){
            JSONObject jsonObject = data2.getJSONObject(i);
            String link = jsonObject.getString("play_url");
            String id = jsonObject.getString("vid");;
            String fileName = id + ".mp4";
            File file = new File(parentPath+fileName);
            HttpVideoUtil.download(link, referer, file);
        }
    }

    public static void main(String[] args) {
//        downloadVideo();
//        downloadVideo();
        getVideo();
    }


}
