package com.example.alioos.controller;

import com.example.alioos.service.OSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * date 2020/7/16
 */
@Controller
@RequestMapping("/oss")
public class OSSController {

    @Autowired
    private OSSService ossService;

    @RequestMapping("/getFile/{fileName}")
    public void getFile(@PathVariable("fileName") String fileName, HttpServletResponse response){
        ossService.getFile(fileName,response);
    }

    @RequestMapping("/uploadFile")
    public void uploadFile(MultipartFile multipartFile){
        ossService.uploadFile(multipartFile);
    }

    @GetMapping("/uploadFolder")
    public Object uploadFolder(){
        return ossService.uploadFolderFile();
    }
}
