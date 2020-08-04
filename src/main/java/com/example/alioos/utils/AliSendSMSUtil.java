package com.example.alioos.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.JsonObject;

/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.5.3</version>
</dependency>
*/
public class AliSendSMSUtil {
	
	public static void sendSMS(String phone, String code) {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "your key", "your secret");
		IAcsClient client = new DefaultAcsClient(profile);

		CommonRequest request = new CommonRequest();
		request.setSysMethod(MethodType.POST);
		request.setSysDomain("dysmsapi.aliyuncs.com");
		request.setSysVersion("2017-05-25");
		request.setSysAction("SendSms");
		request.putQueryParameter("RegionId", "cn-hangzhou");
		request.putQueryParameter("PhoneNumbers", phone);
		request.putQueryParameter("SignName", "VES");
		request.putQueryParameter("TemplateCode", "SMS_196657314");
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", code);
//		String templateParam = JSON.toJSONString(map);
//		request.putQueryParameter("TemplateParam", templateParam);
		request.putQueryParameter("TemplateParam", jsonObject.toString());
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		sendSMS("17816045955","123456");
	}
}
