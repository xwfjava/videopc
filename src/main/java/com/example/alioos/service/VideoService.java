package com.example.alioos.service;

import com.example.alioos.utils.HttpVideoUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * date 2020/7/23
 */
@Service
public class VideoService {

    public void get(HttpServletResponse response){
//        String url = "https://v6-dy-x.ixigua.com/ad04be2cdaba53d1e70db709d57cf3ce/5f196206/video/m/220734a7eb137bd45a3ba56c22c7d3e2c3d1163fb105000010a010122b9e/?a=1128&br=8511&bt=2837&cr=0&cs=0&dr=0&ds=3&er=&l=202007231710030100080611031F00DF07&lr=aweme&mime_type=video_mp4&qs=0&rc=Mzk7N3B3Njg0cDMzM2kzM0ApZzplOTdpOzs4N2llZWloOGdqb2liNmNfbHBfLS1hLS9zczY0L2FgNjM1YWNeXmIvMGI6Yw%3D%3D&vl=&vr=";
//        String url = "https://share.huoshan.com/pages/item/index.html?item_id=6845173270031994127";
//        String url = "https://v29-hs.ixigua.com/7d6914eeab1206c7e3afb742b0b1b231/5f196528/video/m/2203636e259e93c4bf88aa4b1bb32e70d7b1166331b2000062d5b7213990/?a=1112&br=1440&bt=480&cr=0&cs=0&dr=3&ds=3&er=&l=202007231723020100080462200CFCF43C&lr=hotsoon_suffix_vcd&mime_type=video_mp4&qs=0&rc=amxwMzczOm1pdTMzNGYzM0ApaDQ5ZzY8ZGQ1NzUzNjs5aGdkaWIvb3IvLnNfLS1jLS9zc18xYi1fNjA0YjAvL2NgMV46Yw%3D%3D&vl=&vr=";
        String url = "https://api.huoshan.com/hotsoon/item/video/_reflow/?video_id=v0200cf10000brvf1j27u0r420emligg&line=0&app_id=0&vquality=normal&watermark=2&long_video=0&sf=5&ts=1595496181&item_id=6851858997805337856";
        HttpVideoUtil.doGet(url, response);
    }

    public Object get(){
        String url = "https://v3-xg-web.ixigua.com/426139fec0de6d407d184d13f27f95d6/5f1912b9/video/tos/cn/tos-cn-vd-0026/bb4d0e66267f4a159278601d3eb405dd/media-audio-und-mp4a/?a=1768&br=0&bt=0&cr=0&cs=0&dr=0&ds=&er=0&l=202007231106220101980600533101AB32&lr=default&mime_type=video_mp4&qs=0&rc=amY7bzc2NHh5dDMzODczM0ApZzVgcTNiazFra18tLS0tL3NzOmM%3D&vl=&vr=";
        return HttpVideoUtil.doGet(url);
    }
}
