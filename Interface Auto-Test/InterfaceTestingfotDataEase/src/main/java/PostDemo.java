import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PostDemo {
    @Test(description = "Post Demo")
    public void postTest() {
        Response response = given()
                // 设置request Content-Type
                .contentType("application/x-www-form-urlencoded")
                // 请求参数 放body
                .body("UserName=XXXX&Password=XXXXXX&CheckCode=&Remember=false&LoginCheckCode=7505")
                // POST 请求
                .post("http://XXXX.XXXX.com/Home/Login");

        response.print();
    }
    @Test(description = "设置header, cookie的 Get Demo")
    public void getTest(){
        Response response = given()
                .cookie("cookie","value")
                .cookies("cookiename1", "value1", "cookiename2", "value2")
                .header("Accept-Encoding", "gzip, deflate")
                .headers("header1","value1","header2","value2")
                .get("XXXXXXX");

        /*
        获取Response body
        */
        //获取Response body，并转成String类型：
        response.getBody().asString();

    }



}
