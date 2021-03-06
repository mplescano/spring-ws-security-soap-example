package com.bernardomg.example.swss.servlet.encryption.xwss.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor;

import com.bernardomg.example.swss.servlet.common.CommonInterceptorConfig;
import com.bernardomg.example.swss.servlet.common.WsdlConfig;

@Configuration
@EnableWs
@Import(value = { WsdlConfig.class, CommonInterceptorConfig.class })
@ComponentScan("com.bernardomg.example.swss.endpoint")
public class WSEncryptionXwssConfig extends WsConfigurerAdapter {

    @Autowired
    private SoapEnvelopeLoggingInterceptor soapEnvelopeLoggingInterceptor;
    
    @Autowired
    private XwsSecurityInterceptor securityInterceptor;
    
    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(soapEnvelopeLoggingInterceptor);
        interceptors.add(securityInterceptor);
    }
}