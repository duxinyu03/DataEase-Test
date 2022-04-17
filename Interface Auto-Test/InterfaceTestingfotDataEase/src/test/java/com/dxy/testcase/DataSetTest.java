package com.dxy.testcase;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class DataSetTest {
    @Test(description = "POST /dataset/group/isKettleRunning")
    public void testKettleRun(){
        Reporter.log("/dataset/group/isKettleRunning 获取在本地连接模式下抽取和转换来自数据源数据的组件Kettle的运行状态，是点击进入数据集功能模块时执行的操作");
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
                .post("https://dataease.fit2cloud.com/dataset/group/isKettleRunning");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String data = response.jsonPath().getString("data");
            System.out.println("date:" + data);
            response.print();
            Assert.assertEquals(data,"true","接口异常 /dataset/group/isKettleRunning");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /dataset/group/isKettleRunning");
        }
    }

    @Test(description = "GET /engine/mode")
    public void testEngineMode(){
        Reporter.log("/engine/mode 获取数据引擎功能的连接模式（直连模式、本地模式）");
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
                .get("https://dataease.fit2cloud.com/engine/mode");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String data_mode = response.jsonPath().getString("data");
            System.out.println("date:" + data_mode);
            response.print();
            Assert.assertEquals(data_mode,"local","接口异常 /engine/mode");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /engine/mode");
        }
    }

    @Test(description = "POST /authModel/queryAuthModel")
    public void testDataSetInfo(){
        Reporter.log("/authModel/queryAuthModel 获取数据集功能模块中用户的数据集信息");
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
            Assert.assertEquals(data_type,"dataset","接口异常 /authModel/queryAuthModel");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /authModel/queryAuthModel");
        }
    }

    @Test(description = "POST /dataset/group/save")
    public void testSaveGroup(){
        Reporter.log("/dataset/group/save 在数据集模块中新增分组操作");
        // 前置条件：用户已登录状态
        Response response0 = given()
                // 配置SSL 让所有请求支持所有的主机名, 获取公钥，进行SSL加密处理登录信息
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                .body("{\"username\":\"pIEURShFXAPplZvedCZX645fmIRW8fHYZ3RUdmbon6aCuY8PbboNDzB5tSHArY/mtvA86UCq4PcktIKYUeaFdA==\",\"password\":\"j3BseWuSDcZiV8hs6v28F+Wi9d4J8ljCiW6qs1wiWtzZOOC/xGeWg5GXs4KiFQ7ubj7DiCGwFyB185pSV2eWIw==\",\"loginType\":0}")
                .post("https://dataease.fit2cloud.com/api/auth/login");

        Map cookies = response0.getCookies();
        String data_token = response0.jsonPath().get("data.token");

        String table_name = RandomStringUtils.randomAlphanumeric(5);
        String data_dody = "{\"name\": \"新建数据集的分组\", \n" +
                "    \"pid\": \"0\", \n" +
                "    \"level\": 0, \n" +
                "    \"type\": \"group\", \n" +
                "    \"children\": [ ], \n" +
                "    \"sort\": \"type desc,name asc\"}\n";
        JSONObject data_bodys = JSONObject.fromObject(data_dody);
        data_bodys.replace("name","新建数据集的分组","新建数据集的分组"+table_name);

        Response response = given()
                // 写入第一个请求的cookies
                .cookies(cookies)
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                // 设置请求header
                .headers("Authorization",data_token,"Accept","application/json, text/plain, */*")
                .body(data_bodys)
                // POST 请求
                .post("https://dataease.fit2cloud.com/dataset/group/save");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String data_name = response.jsonPath().get("data.name");
            System.out.println("data_name:" + data_name);
            response.print();
            Assert.assertEquals(data_name,data_bodys.getString("name"),"接口异常 /dataset/group/save");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /dataset/group/save");
        }
    }

    @Test(description = "POST /dataset/table/batchAdd")
    public void testAddTable(){
        Reporter.log("/dataset/table/batchAdd 在数据集模块中点击新建数据集“+”操作新增数据库类型的数据集，并添加任意一个数据表");
        // 前置条件：用户已登录状态
        Response response0 = given()
                // 配置SSL 让所有请求支持所有的主机名, 获取公钥，进行SSL加密处理登录信息
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                .body("{\"username\":\"pIEURShFXAPplZvedCZX645fmIRW8fHYZ3RUdmbon6aCuY8PbboNDzB5tSHArY/mtvA86UCq4Pckt" +
                        "IKYUeaFdA==\",\"password\":\"j3BseWuSDcZiV8hs6v28F+Wi9d4J8ljCiW6qs1wiWtzZOOC/xGeWg5GXs4KiFQ7u" +
                        "bj7DiCGwFyB185pSV2eWIw==\",\"loginType\":0}")
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
                .body("[{\"name\":\"demo_chart_viewERoN\",\"sceneId\":\"47f0edd5-1ddc-4953-9362-053b2118d1ed\",\"dataSource" +
                        "Id\":\"76026997-94f9-4a35-96ca-151084638969\",\"type\":\"db\",\"syncType\":\"sync_now\",\"mod" +
                        "e\":0,\"info\":\"{\\\"table\\\":\\\"chart_viewERoN\\\"}\"}]")
                // POST 请求
                .post("https://dataease.fit2cloud.com/dataset/table/batchAdd");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            response.print();
            Assert.assertEquals(result,"true","接口异常 /dataset/table/batchAdd");
        }else{
            System.out.println(status_code);
            response.print();
            throw new RuntimeException("接口异常 接口 /dataset/table/batchAdd");
        }
    }
}
