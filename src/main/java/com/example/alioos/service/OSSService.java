package com.example.alioos.service;

import com.example.alioos.utils.CreateFolderSample;
import com.example.alioos.utils.SimpleGetObjectSample;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * date 2020/7/15
 */
@Service
public class OSSService {

    //上传文件
    public Object uploadFile(MultipartFile file){

        String folder = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))+"/";
        try {
            //新建文件夹
            CreateFolderSample.addFolder(folder);
            //上传文件
            SimpleGetObjectSample.uploadInputStream(file.getInputStream(),file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //文件夹批量文件上传
    public Object uploadFolderFile(){
        String folder = "E:\\tool\\example\\fp1";
        File file = new File(folder);
        boolean directory = file.isDirectory();
        if(!directory){
            return "不存在的文件夹";
        }
        try {
            //新建文件夹
            CreateFolderSample.addFolder(file.getName()+System.currentTimeMillis());
            File[] files = file.listFiles();
            if(files==null || files.length==0){
                return "空文件夹";
            }
            Arrays.stream(files).forEach(item->{
                //上传文件
                try {
                    SimpleGetObjectSample.uploadInputStream(new FileInputStream(item),item.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "操作成功";
    }

    //获取文件
    public void getFile(String fileName, HttpServletResponse response){
        try {
            //获取文件后缀
//            response.setContentType("image/png");
            SimpleGetObjectSample.getFile(fileName,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
