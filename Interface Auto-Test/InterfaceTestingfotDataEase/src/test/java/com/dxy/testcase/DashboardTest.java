package com.dxy.testcase;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class DashboardTest {
    @Test(description = "POST /panel/group/defaultTree")
    public void testDefaultMenus(){
        Reporter.log("/panel/group/defaultTree 获取登录系统后仪表板模块的默认菜单信息");
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
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                // 设置请求header
                .headers("Authorization",data_token,"Accept","application/json, text/plain, */*")
                .body("{\"panelType\":\"system\"}")
                // POST 请求
                .post("https://dataease.fit2cloud.com/panel/group/defaultTree");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String panel = response.jsonPath().get("data[0].pid");
            System.out.println("dashboard panel:" + panel);
            response.print();
            Assert.assertEquals(panel,"default_panel","接口异常 /panel/group/defaultTree");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /panel/group/defaultTree");
        }
    }

    @Test(description = "POST /panel/group/tree")
    public void testTreeMenus(){
        Reporter.log("/panel/group/tree 获取仪表板模块的菜单栏中仪表板信息");
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
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                // 设置请求header
                .headers("Authorization",data_token,"Accept","application/json, text/plain, */*")
                .body("{\"name\":null,\"pid\":null,\"panelType\":\"self\",\"nodeType\":null,\"children\":[],\"sort\":\"create_time desc,node_type desc,name asc\"}")
                // POST 请求
                .post("https://dataease.fit2cloud.com/panel/group/tree");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String panel = response.jsonPath().get("data[0].pid");
            System.out.println("dashboard panel:" + panel);
            response.print();
            Assert.assertEquals(panel,"panel_list","接口异常 /panel/group/tree");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /panel/group/tree");
        }
    }

    @Test(description = "POST /authModel/queryAuthModel")
    public void testAuthModel(){
        Reporter.log("/authModel/queryAuthModel 获取仪表板模块中用户的图表信息");
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
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                // 设置请求header
                .headers("Authorization",data_token,"Accept","application/json, text/plain, */*")
                .body("{\"modelType\":\"chart\"}")
                // POST 请求
                .post("https://dataease.fit2cloud.com/authModel/queryAuthModel");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String data = response.jsonPath().getString("data");
            response.print();
            Assert.assertEquals(result,"true","接口异常 /authModel/queryAuthModel");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /authModel/queryAuthModel");
        }
    }

    @Test(description = "POST /authModel/queryAuthModel 数据集信息")
    public void testDataSetModel(){
        Reporter.log("/authModel/queryAuthModel 获取仪表板模块中用户的数据集信息");
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
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                // 设置请求header
                .headers("Authorization",data_token,"Accept","application/json, text/plain, */*")
                .body("{\"modelType\":\"dataset\"}")
                // POST 请求
                .post("https://dataease.fit2cloud.com/authModel/queryAuthModel");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String data_type = response.jsonPath().get("data[0].modelType");
            response.print();
            Assert.assertEquals(data_type,"dataset","接口异常 /authModel/queryAuthModel 数据集信息");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /authModel/queryAuthModel 数据集信息");
        }
    }
}
