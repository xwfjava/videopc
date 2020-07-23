package com.example.alioos.controller;

import com.example.alioos.service.FfmpegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * date 2020/7/22
 */
@RestController
@RequestMapping("/ffmpeg")
public class FfmpegController {

    @Autowired
    private FfmpegService ffmpegService;

    @GetMapping("/hls")
    public Object hls(){
        return ffmpegService.hls();
    }
}
