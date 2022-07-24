package com.lihd.mvc.config;

import com.lihd.mvc.interceptor.FirstInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;

/**
 * 1 组件扫描 context:component-scan
 * 2 视图解析器 bean:Thymeleaf 视图解析器
 * 3 视图控制器  mvc:view-controller
 * 4 注解驱动 mvc:annotation-driver
 * 5 默认servlet  mvc:default-servlet-handler
 * 6 文件上传  bean:CommonMultipartResolver
 * 7 拦截器  mvc:interceptors
 * 8 异常处理器  bean:SimpleMappingResolver
 *
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/18 19:28
 */
//0 表明是配置类
@Configuration
//1 组件扫描 context:component-scan
@ComponentScan("com.lihd.mvc")
// 4 注解驱动 mvc:annotation-driver
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    //3 视图控制器  mvc:view-controller
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    //5 默认servlet  mvc:default-servlet-handler
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    //6 文件上传  bean:CommonMultipartResolver
//    @Bean
//    public MultipartResolver getMultipartResolver(){
//        return new CommonsMultipartResolver();
//    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }

    //7 拦截器  mvc:interceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        FirstInterceptor firstInterceptor = new FirstInterceptor();

        registry.addInterceptor(firstInterceptor).addPathPatterns("/**").excludePathPatterns("/");

    }


    //8 异常处理器  bean:SimpleMappingResolver
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

        Properties properties = new Properties();
        properties.setProperty("java.lang.Exception","error");

        resolver.setExceptionMappings(properties);
        resolver.setExceptionAttribute("ex");


        resolvers.add(resolver);
    }

    //2 视图解析器 bean:Thymeleaf 视图解析器 -------------------------------------------------------------------------
    //配置生成模板解析器
    @Bean
    public ITemplateResolver templateResolver() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(
                webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    //生成模板引擎并为模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    //生成视图解析器并未解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

}
