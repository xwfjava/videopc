package com.example.alioos.utils;

import com.example.alioos.config.SystemConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * date 2020/7/22
 */
public class ExecUtils {

    //视频分片 生成 m3u8文件
    public static void execSectioning(String filePath, String destination){
        try {
            String exec = SystemConstant.ffmpegPath+" -i "+filePath+" -c:v libx264 -c:a aac -strict -2 -f hls -hls_list_size 0 -hls_time 1 "+destination;
            Process proc =Runtime.getRuntime().exec(exec);
            InputStreamReader inputStreamReader = new InputStreamReader(proc.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.readLine()!=null)
                System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execSectioning2(String filePath, String m3u8Path, String tsPath){
        try {
            String exec = SystemConstant.ffmpegPath+" -i "+filePath+" -c copy -map 0 -f segment -segment_list "+m3u8Path+" -segment_time 5 "+ tsPath;
            Process proc =Runtime.getRuntime().exec(exec);
            InputStreamReader inputStreamReader = new InputStreamReader(proc.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.readLine()!=null)
                System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //去除水印，打马赛克   delogo x 横坐标 y 纵坐标  w 马赛克宽度  h 马赛克高度
    public static void execFilter(String filePath, String destination){
        try {
            String exec = SystemConstant.ffmpegPath+" -i "+filePath+" -filter_complex \"delogo=x=1:y=1:w=320:h=120\",\"delogo=x=390:y=1150:w=320:h=120\" "+destination;
            Process proc =Runtime.getRuntime().exec(exec);
            InputStreamReader inputStreamReader = new InputStreamReader(proc.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.readLine()!=null)
                System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //转码
    public static void convertFilter(String filePath, String destination){
        try {
            String exec = SystemConstant.ffmpegPath+" -i "+filePath+" -s 720*1280 "+destination;
            Process proc =Runtime.getRuntime().exec(exec);
            InputStreamReader inputStreamReader = new InputStreamReader(proc.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.readLine()!=null)
                System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execDel(String filePath){
        try {
            Process proc =Runtime.getRuntime().exec("del "+filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(proc.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.readLine()!=null)
                System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取封面图
    public static void getCoverMap(String filePath, String destination){
        try {
            //跟视频 宽高相同
            String exec = SystemConstant.ffmpegPath+" -i "+filePath+" -y -f image2 -t 0.001 "+destination;
            //固定 宽高
//            String exec = SystemConstant.ffmpegPath+" -i "+filePath+" -y -f image2 -t 1 -s 720x1080 "+destination;
            Process proc =Runtime.getRuntime().exec(exec);
            InputStreamReader inputStreamReader = new InputStreamReader(proc.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.readLine()!=null)
                System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
