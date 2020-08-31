package com.example.alioos.service;

import com.example.alioos.config.SystemConstant;
import com.example.alioos.utils.ExecUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * date 2020/7/22
 */
@Service
public class FfmpegService {

    public String hls(){
        String filePath = "E:\\tool\\example\\火蚁矿业最终成品.mp4";
        String destination = SystemConstant.ffmpegSimplePath+"output.m3u8";
        ExecUtils.execSectioning(filePath, destination);

        return "操作成功";
    }

    public String hls(MultipartFile file){
        String filePath = "E:\\tool\\example\\火蚁矿业最终成品.mp4";
        String destination = SystemConstant.ffmpegSimplePath+"output.m3u8";
        ExecUtils.execSectioning(filePath, destination);
        return "操作成功";
    }
}
