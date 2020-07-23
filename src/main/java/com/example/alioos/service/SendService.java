package com.example.alioos.service;

import com.example.alioos.utils.AliSendSMSUtil;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

/**
 * date 2020/7/17
 */
@Service
public class SendService {

    public Object send(String phone){
        String code = (100000+Math.round(100000*(Math.random())))+"";
        AliSendSMSUtil.sendSMS(phone,code);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("state","1");
        jsonObject.addProperty("msg","发送成功");
        jsonObject.addProperty("code",code);
        return jsonObject.toString();
    }
}
