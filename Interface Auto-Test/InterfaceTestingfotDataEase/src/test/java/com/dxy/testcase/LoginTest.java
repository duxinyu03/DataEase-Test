package com.dxy.testcase;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class LoginTest {
    @Test(description = "POST /api/auth/login")
    public void testLogin(){
        Reporter.log("/api/auth/login 登录系统");
        Response response = given()
                // 配置SSL 让所有请求支持所有的主机名, 获取公钥，进行SSL加密处理登录信息
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                .body("{\"username\":\"pIEURShFXAPplZvedCZX645fmIRW8fHYZ3RUdmbon6aCuY8PbboNDzB5tSHArY/mtvA86UCq4PcktIKYUeaFdA==\",\"password\":\"j3BseWuSDcZiV8hs6v28F+Wi9d4J8ljCiW6qs1wiWtzZOOC/xGeWg5GXs4KiFQ7ubj7DiCGwFyB185pSV2eWIw==\",\"loginType\":0}")
                .post("https://dataease.fit2cloud.com/api/auth/login");

        //rest-assured自带支持JSON数据格式的解析
        String result = response.jsonPath().getString("success");
        System.out.println("post_request result:" + result);
        String message = response.jsonPath().getString("message");
        System.out.println("message:" + message);
        String data_token = response.jsonPath().get("data.token");
        System.out.println("data_token:" + data_token);

        int status_code = response.getStatusCode();
        Assert.assertEquals(status_code, 200, "接口 /api/auth/login");

    }

    @Test(description = "post /api/auth/userInfo",dependsOnMethods = "testLogin")
    public void testUserInfo(){
        Reporter.log("/api/auth/userInfo 获取登录系统的用户信息");
        Response response0 = given()
                // 配置SSL 让所有请求支持所有的主机名, 获取公钥，进行SSL加密处理登录信息
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                .body("{\"username\":\"pIEURShFXAPplZvedCZX645fmIRW8fHYZ3RUdmbon6aCuY8PbboNDzB5tSHArY/mtvA86UCq4PcktIKYUeaFdA==\",\"password\":\"j3BseWuSDcZiV8hs6v28F+Wi9d4J8ljCiW6qs1wiWtzZOOC/xGeWg5GXs4KiFQ7ubj7DiCGwFyB185pSV2eWIw==\",\"loginType\":0}")
                .post("https://dataease.fit2cloud.com/api/auth/login");

        Map cookies = response0.getCookies();
        String data_token = response0.jsonPath().get("data.token");
        Response response = given()
                // 写入第一个请求的cookies
                .cookies(cookies)
                // 设置请求header,传递token值
                .headers("Authorization",data_token,"Accept","application/json, text/plain, */*")
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                // POST 请求
                .post("https://dataease.fit2cloud.com/api/auth/userInfo");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String token = response.jsonPath().get("data.username");
            System.out.println("data_username:" + token);
            Assert.assertEquals(response.print(),"{\"success\":true,\"message\":null,\"data\":{\"userId\":2,\"username\":\"demo\",\"nickName\":\"demo\",\"deptId\":1,\"deptName\":\"默认组织\",\"password\":\"40b8893ea9ebc2d631c4bb42bb1e8996\",\"enabled\":1,\"email\":\"demo@fit2cloud.com\",\"phone\":null,\"language\":\"zh_CN\",\"isAdmin\":false,\"from\":0,\"roles\":[{\"id\":2,\"name\":\"普通员工\"}],\"permissions\":[\"data:read\",\"datasource:read\"]}}","接口异常 /api/auth/userInfo");
        }else if(status_code == 401){
            Assert.assertEquals(status_code, 200, "接口 /api/auth/userInfo 401 Unauthorized");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /api/auth/userInfo");
        }

    }

}
