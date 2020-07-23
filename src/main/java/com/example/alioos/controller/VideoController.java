package com.example.alioos.controller;

import com.example.alioos.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * date 2020/7/23
 */
@Controller
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/getVideo")
    public void get(HttpServletResponse response){
//        response.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        //response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", ":x-requested-with,content-type");
        //((HttpServletResponse)servletResponse).setHeader("Access-Control-Allow-Origin", "*");
        videoService.get(response);
    }

    @RequestMapping("/get1")
    @ResponseBody
    public Object get1(){

        return videoService.get();
    }

    @RequestMapping("/get2/{id}")
    @ResponseBody
    public Object get2(@PathVariable("id") String id){
        return id;
    }
}
