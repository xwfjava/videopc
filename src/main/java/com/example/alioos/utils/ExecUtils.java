package com.example.alioos.utils;

import com.example.alioos.config.SystemConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * date 2020/7/22
 */
public class ExecUtils {

    public static void execSectioning(String filePath){
        try {
            Process proc =Runtime.getRuntime().exec(SystemConstant.ffmpegPath+" -i "+filePath+" -c:v libx264 -c:a aac -strict -2 -f hls -hls_list_size 0 -hls_time 2 "+SystemConstant.ffmpegSimplePath+"output.m3u8");

            InputStreamReader inputStreamReader = new InputStreamReader(proc.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.readLine()!=null)
                System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}
