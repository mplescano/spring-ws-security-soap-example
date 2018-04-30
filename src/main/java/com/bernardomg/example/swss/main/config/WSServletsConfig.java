package com.bernardomg.example.swss.main.config;

import org.springframework.boot.autoconfigure.webservices.WebServicesProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import com.bernardomg.example.swss.servlets.password.digest.wss4j.WSPasswordDigestWss4jContext;
import com.bernardomg.example.swss.servlets.password.digest.xwss.WSPasswordDigestXwssContext;
import com.bernardomg.example.swss.servlets.password.plain.wss4j.WSPasswordPlainWss4jContext;
import com.bernardomg.example.swss.servlets.password.plain.xwss.WSPasswordPlainXwssContext;
import com.bernardomg.example.swss.servlets.unsecure.WSUnsecureContext;

@Configuration
@EnableConfigurationProperties(WebServicesProperties.class)
public class WSServletsConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> wsUnsecureServlet(WebServicesProperties properties) {
        String path = "/unsecure/*";
        Class<WSUnsecureContext> classParam = WSUnsecureContext.class;
        return buildServletRegistration(properties, path, classParam);
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> wsPasswordPlainWss4jServlet(WebServicesProperties properties) {
    	String path = "/password/plain/wss4j/*";
        Class<WSPasswordPlainWss4jContext> classParam = WSPasswordPlainWss4jContext.class;
        return buildServletRegistration(properties, path, classParam);
    }
    
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> wsPasswordDigestWss4jServlet(WebServicesProperties properties) {
    	String path = "/password/digest/wss4j/*";
        Class<WSPasswordDigestWss4jContext> classParam = WSPasswordDigestWss4jContext.class;
        return buildServletRegistration(properties, path, classParam);
    }
    
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> wsPasswordPlainXwssServlet(WebServicesProperties properties) {
    	String path = "/password/plain/xwss/*";
        Class<WSPasswordPlainXwssContext> classParam = WSPasswordPlainXwssContext.class;
        return buildServletRegistration(properties, path, classParam);
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> wsPasswordDigestXwssServlet(WebServicesProperties properties) {
    	String path = "/password/digest/xwss/*";
        Class<WSPasswordDigestXwssContext> classParam = WSPasswordDigestXwssContext.class;
        return buildServletRegistration(properties, path, classParam);
    }
    
	private ServletRegistrationBean<MessageDispatcherServlet> buildServletRegistration(
			WebServicesProperties properties, String path,
			Class<?> classParam) {
		AnnotationConfigWebApplicationContext wsServletContext = new AnnotationConfigWebApplicationContext();
        wsServletContext.register(classParam);
        MessageDispatcherServlet servlet = new MessageDispatcherServlet(wsServletContext);
        servlet.setTransformWsdlLocations(true);
        servlet.setTransformSchemaLocations(true);
        String urlMapping = (path.endsWith("/") ? path + "*" : (path.endsWith("/*") ? path : path + "/*"));
        ServletRegistrationBean<MessageDispatcherServlet> registration = new ServletRegistrationBean<>(
                servlet, urlMapping);
        WebServicesProperties.Servlet servletProperties = properties.getServlet();
        registration.setLoadOnStartup(servletProperties.getLoadOnStartup());
        servletProperties.getInit().forEach(registration::addInitParameter);
        registration.setName(classParam.getCanonicalName());
        return registration;
	}
}
