package org.lagou.config;

import org.lagou.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * spring-web初始化器
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {

        System.out.println(Thread.currentThread().getName()+"========>tomcat启动成功，异步执行servlet3.0新特性回调函数(设置springmvc的DispatcherServlet)");
        //  获取全局唯一的容器对象
        AnnotationConfigWebApplicationContext ac = SpringApplication.applicationContext;

        // Create and register the DispatcherServlet
        //基于java代码的方式初始化DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");

    }
}
