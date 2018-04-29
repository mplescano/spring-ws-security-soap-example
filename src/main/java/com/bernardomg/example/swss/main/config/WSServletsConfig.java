package com.bernardomg.example.swss.main.config;

import org.springframework.boot.autoconfigure.webservices.WebServicesProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import com.bernardomg.example.swss.unsecure.WSUnsecureContext;

@Configuration
@EnableConfigurationProperties(WebServicesProperties.class)
public class WSServletsConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> wsUnsecureServlet(WebServicesProperties properties) {
        AnnotationConfigWebApplicationContext wsUnsecureServeltContext = new AnnotationConfigWebApplicationContext();
        wsUnsecureServeltContext.register(WSUnsecureContext.class);
        MessageDispatcherServlet servlet = new MessageDispatcherServlet(wsUnsecureServeltContext);//TODO
        //servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        servlet.setTransformSchemaLocations(true);
        String path = "/unsecure/*";
        String urlMapping = (path.endsWith("/") ? path + "*" : path + "/*");
        ServletRegistrationBean<MessageDispatcherServlet> registration = new ServletRegistrationBean<>(
                servlet, urlMapping);
        WebServicesProperties.Servlet servletProperties = properties.getServlet();
        registration.setLoadOnStartup(servletProperties.getLoadOnStartup());
        servletProperties.getInit().forEach(registration::addInitParameter);
        registration.setName("wsUnsecureServlet");
        return registration;
    }
    
}
