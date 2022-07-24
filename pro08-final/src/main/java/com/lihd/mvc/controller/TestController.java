package com.lihd.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/18 20:08
 */
@Controller
public class TestController {

//    @RequestMapping("/")
//    public String index(){
//        return "index";
//    }


    @RequestMapping("/upload")
    public String upload(MultipartFile photo, HttpSession session) throws IOException {
//        MultipartResolver multipartResolver;
        String name = photo.getName();
        String filename = photo.getOriginalFilename();
        System.out.println("name = " + name);
        System.out.println("filename = " + filename);

        ServletContext application = session.getServletContext();
        String photoPath = application.getRealPath("photo");

        File file = new File(photoPath);
        if(!file.exists()){
            file.mkdir();
        }

        String finalPath = photoPath + File.separator + filename;

        photo.transferTo(new File(finalPath));

        return "persist";
    }


    @RequestMapping("/testExceptionResolver")
    public String testExceptionResolver(){

        System.out.println(1/0);

        return "persist";
    }


}
