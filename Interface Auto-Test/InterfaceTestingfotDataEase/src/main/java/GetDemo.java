import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetDemo {
    @Test(description = "Get Demo")
    public void getHttpTest(){
        Response response = given().get("");
        // 打印出 response 的body
        response.print();
    }
    @Test(description = "HTTPS协议的Get Demo")
    public void getHttpsTest() {
        Response response = given()
                // 配置SSL 让所有请求支持所有的主机名
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .params("q", "自动化测试", "start", 0, "count", 2)
                .get("https://api.douban.com/v2/book/search");
        // 打印出 response 的body
        response.print();
    }
    @Test()
    public void getHttpsTest2() {
        Response response = given()
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .param("q", "自动化测试")
                .param("start", 0)
                .param("count", 2)
                .get("https://api.douban.com/v2/book/search");
        // 打印出 response 的body
        response.print();

        //获取状态码，返回int类型：
        response.getStatusCode();
        //获取具体的某个cookies：
        response.getCookie("cookiesName");
        //获取所有的cookies，返回一个map:
        Map cookies = response.getCookies();

        /*
        Response headers 常用操作
         */
        //1.获取所有headers：
        Headers headers = response.getHeaders();
        //2.获取指定header：
        response.getHeader("header name");
//        // 等同上面方法
//        Headers headers = response.getHeaders();
//        headers.get("header name");

        //3.判断某个header是否存在，返回boolean类型
//        Headers headers = response.getHeaders();
        headers.hasHeaderWithName("XXX");
        //4.判断header是否为空
//        Headers headers = response.getHeaders();
        headers.exist();


    }
}
