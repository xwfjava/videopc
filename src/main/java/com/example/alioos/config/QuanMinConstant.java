package com.example.alioos.config;

/**
 * date 2020/7/24
 */
public class QuanMinConstant {
    //获取热门视频
    public static String hotVideoUrl = "https://quanmin.baidu.com/wise/growth/homepage?_format=json";

    //分类 rn：获取条数 ， pn：页码 从一开始 ， tab_name：类型名称
    public static String typeVideoUrlExample = "https://quanmin.baidu.com/wise/growth/tab?rn=12&pn=1&timestamp=1595570533214&_format=json&tab_name=caiyi";

    public static String path = "E:\\tool\\example\\quanmin\\";



    enum TabName{
        tuijian("推荐"),
        zhishi("知识"),
        gaoxiao("搞笑"),
        mingxing("明星"),
        dongzhiwu("动植物"),
        meishi("美食"),
        qinggan("情感"),
        youxi("游戏"),
        dongman("动漫"),
        renwen("人文"),
        shishang("时尚"),
        yundong("运动"),
        shishi("时事"),
        more("更多");

        String desc;
        TabName(String desc){
            this.desc=desc;
        }
    }
}
