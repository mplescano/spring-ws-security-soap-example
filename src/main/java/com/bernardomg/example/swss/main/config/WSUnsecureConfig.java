package com.bernardomg.example.swss.main.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.webservices.WebServicesProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

@EnableWs
@Configuration
@EnableConfigurationProperties(WebServicesProperties.class)
public class WSUnsecureConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(WebServicesProperties properties) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet(null);//TODO
        //servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        servlet.setTransformSchemaLocations(true);
        String path = properties.getPath();
        String urlMapping = (path.endsWith("/") ? path + "*" : path + "/*");
        ServletRegistrationBean<MessageDispatcherServlet> registration = new ServletRegistrationBean<>(
                servlet, urlMapping);
        WebServicesProperties.Servlet servletProperties = properties.getServlet();
        registration.setLoadOnStartup(servletProperties.getLoadOnStartup());
        //servletProperties.getInit().forEach(registration::addInitParameter);
        return registration;
    }
    
}
