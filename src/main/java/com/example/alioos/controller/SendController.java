package com.example.alioos.controller;

import com.example.alioos.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * date 2020/7/17
 */
@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    private SendService sendService;

    @GetMapping("/sms/{phone}")
    public Object sendSms(@PathVariable("phone") String phone){
        return sendService.send(phone);
    }
}
