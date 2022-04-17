package com.dxy.testcase;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class FrontTest {
    @Test(description = "POST /api/dynamicMenu/menus")
    public void testMenus(){
        Reporter.log("/api/dynamicMenu/menus 获取登录系统后首页模块的菜单信息");
        // 前置条件：用户已登录状态
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
                // 设置请求header
                .headers("Authorization",data_token,"Accept","application/json, text/plain, */*")
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                // POST 请求
                .post("https://dataease.fit2cloud.com/api/dynamicMenu/menus");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String index_title = response.jsonPath().get("data[0].meta.title");
            System.out.println("title:" + index_title);
            response.print();
            Assert.assertEquals(index_title,"Home Page","接口异常 /api/auth/userInfo");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /api/auth/userInfo");
        }
    }

    @Test(description = "POST /api/auth/logout")
    public void teatLogOut(){
        Reporter.log("/api/auth/logout 退出系统操作");
        // 前置条件：用户已登录状态
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
                // 设置请求header，传递token值
                .headers("Authorization",data_token,"Accept","application/json, text/plain, */*")
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                // POST 请求
                .post("https://dataease.fit2cloud.com/api/auth/logout");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String data = response.jsonPath().getString("data");
            System.out.println("date:" + data);
            response.print();
            Assert.assertEquals(data,"success","接口异常 /api/auth/logout");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /api/auth/logout");
        }
    }
}
