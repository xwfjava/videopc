package com.example.alioos.utils;

import com.example.alioos.config.SystemConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * date 2020/7/22
 */
public class ExecUtils {

    public static void exec(String filePath){
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
}
