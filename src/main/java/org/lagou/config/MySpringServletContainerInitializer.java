package org.lagou.config;

import org.springframework.web.WebApplicationInitializer;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * 基于SPI机制，servlet3.0规范回调器
 */
@HandlesTypes({WebApplicationInitializer.class})
public class MySpringServletContainerInitializer implements ServletContainerInitializer {

    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {}
}