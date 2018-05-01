package com.bernardomg.example.swss.main.config;

import org.springframework.boot.autoconfigure.webservices.WebServicesProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import com.bernardomg.example.swss.servlet.encryption.wss4j.WSEncryptionWss4jContext;
import com.bernardomg.example.swss.servlet.password.digest.wss4j.WSPasswordDigestWss4jContext;
import com.bernardomg.example.swss.servlet.password.digest.xwss.WSPasswordDigestXwssContext;
import com.bernardomg.example.swss.servlet.password.plain.wss4j.WSPasswordPlainWss4jContext;
import com.bernardomg.example.swss.servlet.password.plain.xwss.WSPasswordPlainXwssContext;
import com.bernardomg.example.swss.servlet.signature.wss4j.WSSignatureWss4jContext;
import com.bernardomg.example.swss.servlet.signature.xwss.WSSignatureXwssContext;
import com.bernardomg.example.swss.servlet.unsecure.WSUnsecureContext;

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
    public ServletRegistrationBean<MessageDispatcherServlet> wsSignatureWss4jServlet(WebServicesProperties properties) {
    	String path = "/signature/wss4j/*";
        Class<WSSignatureWss4jContext> classParam = WSSignatureWss4jContext.class;
        return buildServletRegistration(properties, path, classParam);
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> wsEncryptionWss4jServlet(WebServicesProperties properties) {
        String path = "/encryption/wss4j/*";
        Class<WSEncryptionWss4jContext> classParam = WSEncryptionWss4jContext.class;
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
    
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> wsSignatureXwssServlet(WebServicesProperties properties) {
        String path = "/signature/xwss/*";
        Class<WSSignatureXwssContext> classParam = WSSignatureXwssContext.class;
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
