package org.lagou.config;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

@Component
public class AppConfig {

    public static final String DEFAULT_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";

    @Bean
    Tomcat tomcat(){
        //实例化一个Tomcat
        Tomcat tomcat = new Tomcat();
        File baseDir = createTempDir("tomcat");
        //设置Tomcat的工作临时目录
        tomcat.setBaseDir(baseDir.getAbsolutePath());
        try {
            tomcat.addWebapp("/","/Users/wangyang/it/workspace/study-project/lagou/zdy-springboot/");
        } catch (ServletException e) {
            e.printStackTrace();
        }
        //默认使用Http11NioProtocal实例化Connector
        Connector connector = new Connector(DEFAULT_PROTOCOL);
        connector.setPort(8080);
       // connector.setThrowOnFailure(true);
        //给Service添加Connector
        tomcat.getService().addConnector(connector);
        //customizeConnector(connector);
        tomcat.setConnector(connector);
        //关闭热部署
        tomcat.getHost().setAutoDeploy(false);
        //配置Engine
        return tomcat;
    }


    private File createTempDir(String prefix) {
        try {
            File tempDir = File.createTempFile(prefix + ".", "." + "自定义");
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir;
        }
        catch (IOException ex) {

            ex.printStackTrace();
         }
        return null;
    }



}
