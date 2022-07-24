package com.lihd.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/17 23:35
 */
@Controller
public class FileController {


    @RequestMapping("/uploadFile")
    public String uploadFile(MultipartFile photo, HttpSession session) throws IOException {
        MultipartResolver multipartResolver;
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


    @RequestMapping("/uploadFileUUID")
    public String uploadFileUUID(MultipartFile photo, HttpSession session) throws IOException {
        MultipartResolver multipartResolver;
        String name = photo.getName();
        String filename = photo.getOriginalFilename();
        System.out.println("name = " + name);
        System.out.println("filename = " + filename);

        //生成uuid的文件名 保证不会重复
        String uuid = UUID.randomUUID().toString();
        String suffix = filename.substring(filename.lastIndexOf('.'));

        filename = uuid + suffix;


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

    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session,String filename,String filepath) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
//        String realPath = servletContext.getRealPath("/static/img/"+filename);
        String realPath = servletContext.getRealPath(filepath+filename);
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename="+filename);
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }



}
