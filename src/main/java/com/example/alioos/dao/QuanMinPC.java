package com.example.alioos.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.alioos.config.QuanMinConstant;
import com.example.alioos.utils.ExecUtils;
import com.example.alioos.utils.HttpVideoUtil;
import com.example.alioos.utils.QiNiuYunUtil;

import java.io.File;

/**
 * date 2020/7/23
 */
//@Service
public class QuanMinPC {

    public static String referer = "https://quanmin.baidu.com";
    public static String domain = "http://qetum61k8.bkt.clouddn.com/";

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
//            getHot(typeName, parentPath);
            //非热门视频下载
            for (int h=6;h<15;h++){
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
                    String id = jsonObject.getString("vid");
                    if(getCount(id)){
                        continue;
                    }
                    String title = jsonObject.getString("title");
                    String time_length = jsonObject.getString("time_length");
                    String[] split = time_length.split(":");
                    String seconds = Integer.parseInt(split[0])*60+Integer.parseInt(split[1])+"";
                    String fileName = id + ".mp4";
                    String filePath = parentPath+fileName;
                    File file = new File(filePath);
                    HttpVideoUtil.download(link, referer, file);
                    //裁剪封面
                    String coverName = id+".jpg";
                    String coverPath = "E:\\tool\\example\\quanmin\\image\\"+coverName;
                    ExecUtils.getCoverMap(filePath, coverPath);
                    //上传视频
                    QiNiuYunUtil.add(filePath, fileName);
                    //上传封面
                    QiNiuYunUtil.add(coverPath, coverName);
                    //保存
                    QiNiuUtil.insert(domain+coverName,domain+fileName, title,seconds);
                }
            }
        }
    }


    public static void getVideo(String typeStr, int pn){
        QuanMinConstant.TabName[] values = QuanMinConstant.TabName.values();
        boolean flag = false;
        for (int k=0;k<values.length;k++){
            String typeName = values[k].name();
            if(typeName.equals(typeStr)){
                flag =true;
            }
            if(!flag){
                continue;
            }
            String parentPath = QuanMinConstant.path+typeName+"\\";
            File parentfile = new File(parentPath);
            if(!parentfile.exists()){
                parentfile.mkdirs();
            }
            //热门视频下载
            if(pn ==1){
                getHot(typeName, parentPath);
            }
            //非热门视频下载
            for (int h=pn;h<15;h++){
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
                    String id = jsonObject.getString("vid");
                    if(getCount(id)){
                        continue;
                    }
                    String title = jsonObject.getString("title");
                    String time_length = jsonObject.getString("time_length");
                    String[] split = time_length.split(":");
                    String seconds = Integer.parseInt(split[0])*60+Integer.parseInt(split[1])+"";
                    String fileName = id + ".mp4";
                    String filePath = parentPath+fileName;
                    File file = new File(filePath);
                    HttpVideoUtil.download(link, referer, file);
                    //裁剪封面
                    String coverName = id+".jpg";
                    String coverPath = "E:\\tool\\example\\quanmin\\image\\"+coverName;
                    ExecUtils.getCoverMap(filePath, coverPath);
                    //上传视频
                    QiNiuYunUtil.add(filePath, fileName);
                    //上传封面
                    QiNiuYunUtil.add(coverPath, coverName);
                    //保存
                    QiNiuUtil.insert(domain+coverName,domain+fileName, title,seconds);
                }
            }
            pn =6;
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
            String id = jsonObject.getString("vid");
            if(getCount(id)){
                continue;
            }
            String title = jsonObject.getString("title");
            String time_length = jsonObject.getString("time_length");
            String[] split = time_length.split(":");
            String seconds = Integer.parseInt(split[0])*60+Integer.parseInt(split[1])+"";
            String fileName = id + ".mp4";
            //下载视频到本地
            String filePath = parentPath+fileName;
            File file = new File(filePath);
            HttpVideoUtil.download(link, referer, file);
            //裁剪封面
            String coverName = id+".jpg";
            String coverPath = "E:\\tool\\example\\quanmin\\image\\"+coverName;
            ExecUtils.getCoverMap(filePath, coverPath);
            //上传视频
            QiNiuYunUtil.add(filePath, fileName);
            //上传封面
            QiNiuYunUtil.add(coverPath, coverName);
            //保存
            QiNiuUtil.insert(domain+coverName,domain+fileName, title,seconds);
        }
    }

    public static void main(String[] args) {
//        downloadVideo();
//        downloadVideo();
//        getVideo();
        getVideoByType("shishang",16);
//        getVideo();
    }


    private static boolean getCount(String id){
        String sql = "select count(1) from yzcm_videos_1 where cmc_video_path like '%"+id+"%'";
        String string1 = JDBCUtil.getString1(sql);
        return string1.equals("0")?false:true;
    }


    public static void getVideoByType(String typeStr, int pn){
        String parentPath = QuanMinConstant.path+typeStr+"\\";
        File parentfile = new File(parentPath);
        //非热门视频下载
        for (int h=pn;h<100;h++){
            //替换下载路径
            String url = String.format(QuanMinConstant.typeVideoUrl, h, typeStr);
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
                String id = jsonObject.getString("vid");
                if(getCount(id)){
                    continue;
                }
                String title = jsonObject.getString("title");
                String time_length = jsonObject.getString("time_length");
                String[] split = time_length.split(":");
                String seconds = Integer.parseInt(split[0])*60+Integer.parseInt(split[1])+"";
                String fileName = id + ".mp4";
                String filePath = parentPath+fileName;
                File file = new File(filePath);
                HttpVideoUtil.download(link, referer, file);
                //裁剪封面
                String coverName = id+".jpg";
                String coverPath = "E:\\tool\\example\\quanmin\\image\\"+coverName;
                ExecUtils.getCoverMap(filePath, coverPath);
                //上传视频
                QiNiuYunUtil.add(filePath, fileName);
                //上传封面
                QiNiuYunUtil.add(coverPath, coverName);
                //保存
                QiNiuUtil.insert(domain+coverName,domain+fileName, title,seconds);
            }
        }
    }
}
