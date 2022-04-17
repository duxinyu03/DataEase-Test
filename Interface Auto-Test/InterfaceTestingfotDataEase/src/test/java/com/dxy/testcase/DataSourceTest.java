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

public class DataSourceTest {
    @Test(description = "POST /datasource/add/")
    public void testAddDataSource(){
        Reporter.log("/datasource/add/ 在数据源功能模块中新增数据源操作");
        // 前置条件：用户已登录状态
        Response response0 = given()
                // 配置SSL 让所有请求支持所有的主机名, 获取公钥，进行SSL加密处理登录信息
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                .body("{\"username\":\"pIEURShFXAPplZvedCZX645fmIRW8fHYZ3RUdmbon6aCuY8PbboNDzB5tSHArY/mtvA86UCq4PcktIKYUeaFdA==\",\"password\":\"j3BseWuSDcZiV8hs6v28F+Wi9d4J8ljCiW6qs1wiWtzZOOC/xGeWg5GXs4KiFQ7ubj7DiCGwFyB185pSV2eWIw==\",\"loginType\":0}")
                .post("https://dataease.fit2cloud.com/api/auth/login");

        Map cookies = response0.getCookies();
        String data_token = response0.jsonPath().get("data.token");

        String table_name = RandomStringUtils.randomAlphanumeric(3);
        String data_body = "{\"configuration\":\"{\\\"initialPoolSize\\\":5,\\\"extraParams\\\":\\\"characterEncoding=UTF-8&connectTimeout=5000&useSSL=false&allowPublicKeyRetrieval=true\\\",\\\"minPoolSize\\\":5,\\\"maxPoolSize\\\":50,\\\"maxIdleTime\\\":30,\\\"acquireIncrement\\\":5,\\\"idleConnectionTestPeriod\\\":5,\\\"connectTimeout\\\":5,\\\"dataSourceType\\\":\\\"jdbc\\\",\\\"host\\\":\\\"127.0.0.1\\\",\\\"dataBase\\\":\\\"MySQL\\\",\\\"port\\\":\\\"8653\\\"}\",\"apiConfiguration\":[],\"name\":\"测试新建数据源mysql类型\",\"type\":\"mysql\"}";
        JSONObject data_bodys = JSONObject.fromObject(data_body);
        data_bodys.replace("name","测试新建数据源mysql类型","测试新建数据源mysql类型"+table_name);

        Response response = given()
                // 写入第一个请求的cookies
                .cookies(cookies)
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                // 设置请求header
                .headers("Authorization",data_token,"Accept","application/json, text/plain, */*")
                .body(data_bodys)
                // POST 请求
                .post("https://dataease.fit2cloud.com/datasource/add/");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String data_name = response.jsonPath().get("data.name");
            response.print();
            Assert.assertEquals(data_name,data_bodys.getString("name"),"接口异常 /datasource/add/");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /datasource/add/");
        }
    }

    @Test(description = "GET /datasource/list/mysql", dependsOnMethods = "testAddDataSource")
    public void testAddDisplay(){
        Reporter.log("/dataset/table/batchAdd 验证新增数据源操作的模块数据展示结果");
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
                // GET 请求
                .get("https://dataease.fit2cloud.com/datasource/list/mysql");

        int status_code = response.getStatusCode();
        if(status_code == 200){
            //rest-assured自带支持JSON数据格式的解析
            String result = response.jsonPath().getString("success");
            System.out.println("post_request result:" + result);
            String data_body = response.jsonPath().get("data[-1].name");
            System.out.println("date_name:" + data_body);
            response.print();
            Assert.assertEquals(true,data_body.startsWith("测试新建数据源mysql类型"),"接口异常 /datasource/list/mysql");
        }else{
            System.out.println(status_code);
            throw new RuntimeException("接口异常 接口 /datasource/list/mysql");
        }
    }

}
