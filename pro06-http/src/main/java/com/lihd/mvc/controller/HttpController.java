package com.lihd.mvc.controller;

import com.lihd.mvc.pojo.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/17 15:39
 */
@Controller
public class HttpController {

    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody){
        //, @RequestHeader String requestHeader
//        System.out.println("requestHeader = " + requestHeader);
        System.out.println("requestBody = " + requestBody);
        //requestBody = username=%E5%90%95%E5%B8%83&password=123

        return "persist";
    }

    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String > requestEntity){

        HttpHeaders headers = requestEntity.getHeaders();
        String body = requestEntity.getBody();

        System.out.println("headers = " + headers);
        System.out.println("body = " + body);

        return "persist";
    }

    @RequestMapping("/testResponse")
    public String testResponse(HttpServletResponse response) throws IOException {


        //这里的异常一定要抛出 不建议自己处理
        PrintWriter writer = response.getWriter();
        writer.write("除非我不想赢 否则我一定赢");

        return null;
    }

    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody(){

        return "i just a little boy";
    }

    @RequestMapping("testResponseBean")
    @ResponseBody
    public User testResponseBean(){
        return new User(1001, "李白", "太白");
    }


    @RequestMapping("/testResponseAjax")
    @ResponseBody
    public String testResponseAjax(String username,String password){
        System.out.println("守护最好的AJAX");
        return "你的名字是 : "+username + ",你的密码是 : " + password;
    }

}
