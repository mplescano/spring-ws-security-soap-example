package com.bernardomg.example.swss.main.config;

import org.springframework.boot.autoconfigure.webservices.WebServicesProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import com.bernardomg.example.swss.password.plain.wss4j.WSPasswordPlainWss4jContext;
import com.bernardomg.example.swss.unsecure.WSUnsecureContext;

@Configuration
@EnableConfigurationProperties(WebServicesProperties.class)
public class WSServletsConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> wsUnsecureServlet(WebServicesProperties properties) {
        AnnotationConfigWebApplicationContext wsServletContext = new AnnotationConfigWebApplicationContext();
        wsServletContext.register(WSUnsecureContext.class);
        MessageDispatcherServlet servlet = new MessageDispatcherServlet(wsServletContext);//TODO
        //servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        servlet.setTransformSchemaLocations(true);
        String path = "/unsecure/*";
        String urlMapping = (path.endsWith("/") ? path + "*" : (path.endsWith("/*") ? path : path + "/*"));
        ServletRegistrationBean<MessageDispatcherServlet> registration = new ServletRegistrationBean<>(
                servlet, urlMapping);
        WebServicesProperties.Servlet servletProperties = properties.getServlet();
        registration.setLoadOnStartup(servletProperties.getLoadOnStartup());
        servletProperties.getInit().forEach(registration::addInitParameter);
        registration.setName(WSUnsecureContext.class.getCanonicalName());
        return registration;
    }
    
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> wsPasswordPlainWss4jServlet(WebServicesProperties properties) {
        AnnotationConfigWebApplicationContext wsServletContext = new AnnotationConfigWebApplicationContext();
        wsServletContext.register(WSPasswordPlainWss4jContext.class);
        MessageDispatcherServlet servlet = new MessageDispatcherServlet(wsServletContext);//TODO
        //servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        servlet.setTransformSchemaLocations(true);
        String path = "/password/plain/wss4j/*";
        String urlMapping = (path.endsWith("/") ? path + "*" : (path.endsWith("/*") ? path : path + "/*"));
        ServletRegistrationBean<MessageDispatcherServlet> registration = new ServletRegistrationBean<>(
                servlet, urlMapping);
        WebServicesProperties.Servlet servletProperties = properties.getServlet();
        registration.setLoadOnStartup(servletProperties.getLoadOnStartup());
        servletProperties.getInit().forEach(registration::addInitParameter);
        registration.setName(WSPasswordPlainWss4jContext.class.getCanonicalName());
        return registration;
    }
}
