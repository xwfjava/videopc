package com.example.alioos.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * date 2020/8/8
 */
public class QiNiuYunUtil {
    public static void main(String[] args) {
        Configuration cfg = new Configuration(Region.region0());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "your accessKey";
        String secretKey = "your secretKey";
        String bucket = "your bucket";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "E:\\tool\\example\\pc2\\6846298830045039872.mp4";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "6846298830045039872.mp4";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket,key,3600*24,null);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    public static void deleteBatch() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
//...其他参数参考类注释
        String accessKey = "your access key";
        String secretKey = "your secret key";
        String bucket = "your bucket name";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            //单次批量请求的文件数量不得超过1000
            String[] keyList = new String[]{
                    "qiniu.jpg",
                    "qiniu.mp4",
                    "qiniu.png",
            };
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, keyList);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < keyList.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keyList[i];
                System.out.print(key + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                } else {
                    System.out.println(status.data.error);
                }
            }
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
    }

    public static void delete(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
//...其他参数参考类注释
        String accessKey = "your access key";
        String secretKey = "your secret key";
        String bucket = "your bucket name";
        String key = "your file key";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    public static void add(String localFilePath, String key) {
        Configuration cfg = new Configuration(Region.region0());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "your access key";
        String secretKey = "your secret key";
        String bucket = "your bucket name";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
//        String localFilePath = "E:\\tool\\example\\pc2\\6845306166726200589.mp4";
//默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = "0_"+System.currentTimeMillis()+".mp4";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket,key,3600,null);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            ex.printStackTrace();
        }
    }

    public static void videoAudit(){
//...其他参数参考类注释
        String accessKey = "your access key";
        String secretKey = "your secret key";
        Auth auth = Auth.create(accessKey, secretKey);

        String url = "http://ai.qiniuapi.com/v3/video/censor";
        String host = "ai.qiniuapi.com";
        String data = "{\"data\":{\"uri\": \"https://cdn1.k1chian.com/1596876955770.mp4\"},\"params\": {\"scenes\": [\"pulp\"],\"hook_url\": \"http://xwftest1.vaiwan.com/VIDEO/video/auditResult\",\"cut_param\": {\"interval_msecs\": 5000}}}";
        String contentType = "application/json";
        String method = "POST";
        StringMap stringMap = auth.authorizationV2(url, method, data.getBytes(), contentType);
        String qiniuToken = stringMap.get("Authorization").toString();
        System.out.println(qiniuToken);
        //头部部分
        StringMap header = new StringMap();
        header.put("Host",host);
        header.put("Authorization",qiniuToken);
        header.put("Content-Type", contentType);
        Configuration c = new Configuration(Region.huadong());
        Client client = new Client(c);
        try {
            Response response = client.post(url, data.getBytes(), header, contentType);
            JSONObject checkResult = JSON.parseObject(response.bodyString());
            System.out.println(checkResult);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    public static void videoAuditResult(){
//...其他参数参考类注释
        String accessKey = "your access key";
        String secretKey = "your secret key";
        Auth auth = Auth.create(accessKey, secretKey);
        String jobId = "5f4a030c6821180007b3e175";
        String url = "http://ai.qiniuapi.com/v3/jobs/video/"+jobId;
        String host = "ai.qiniuapi.com";
        String method = "GET";
        StringMap stringMap = auth.authorizationV2(url, method, null, null);
        String qiniuToken = stringMap.get("Authorization").toString();
        System.out.println(qiniuToken);
        //头部部分
        StringMap header = new StringMap();
        header.put("Host",host);
        header.put("Authorization",qiniuToken);
        Configuration c = new Configuration(Region.huadong());
        Client client = new Client(c);
        try {
            Response response = client.get(url,header);
            JSONObject checkResult = JSON.parseObject(response.bodyString());
            System.out.println(checkResult);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    public static void main2(String[] args) {
        videoAuditResult();
    }
}
