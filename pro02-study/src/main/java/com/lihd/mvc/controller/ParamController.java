package com.lihd.mvc.controller;

import com.lihd.mvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/15 13:19
 */
@Controller
@RequestMapping("/param")
public class ParamController {

    @RequestMapping("/index")
    public String index(){

        return "param_index";
    }

    //通过原生的request获取请求参数
    @RequestMapping("/testRequest")
    public String testRequest(HttpServletRequest request){

        HttpSession session = request.getSession();


        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String[] hobbies = request.getParameterValues("hobby");

        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("hobbies = " + Arrays.toString(hobbies));

        return "persist";
    }

    //这里是通过构造器形参获取请求参数 ： 要求 参数名不能乱写 要与表单中的name属性值相等
    //否则获取的值就是null
    @RequestMapping("/testForm")
    public String testForm(String username,String password,String hobby){
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("hobby = " + hobby);//hymeleaf,MyBaits,Spring 也可以使用数组完成哦

        return "persist";
    }

    //如果有了 value = "xxx" 那么就必须带上这个值 否则 HTTP Status 400 - Required String parameter 'user_name' is not present
    //但是 设置了

    @RequestMapping("/paramHeaderCookie")
    public String paramHeaderCookie(
            @RequestParam(value = "user_name",required = false,defaultValue = "admin") String username,
            @RequestParam(value = "pass_word",defaultValue = "root") String password,
            String hobby,
            @RequestHeader String host,
            @RequestHeader(value = "love",defaultValue = "none") String love,
            @CookieValue String JSESSIONID,
            @CookieValue(value = "myCookie", defaultValue = "123") String myCookie
    ){

        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("hobby = " + hobby);

        System.out.println("host = " + host);
        System.out.println("love = " + love);
        System.out.println("JSESSIONID = " + JSESSIONID);
        System.out.println("myCookie = " + myCookie);


        return "persist";
    }


    @RequestMapping("/testPojo")
    public String testPojo(User user){
        //user对象的值 真的被赋上去了 可能是反射 直接newInstance 然后获取类的属性一个一个set进去
        //但是中文乱码 应该去 filter中 设置编码
        System.out.println("user = " + user);
        return "persist";
    }


}
