package com.bernardomg.example.swss.servlets.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor;

@Configuration
public class CommonInterceptorConfig {

    @Bean
    public SoapEnvelopeLoggingInterceptor soapEnvelopeLoggingInterceptor() {
        return new SoapEnvelopeLoggingInterceptor();
    }
    
    @Bean
    public PayloadValidatingInterceptor payloadValidatingInterceptor(@Value("${schema.path}") Resource schemaPath,
                                                                     @Value("${interceptor.payloadValidator.validateRequest}") boolean validateRequest,
                                                                     @Value("${interceptor.payloadValidator.validateResponse}") boolean validateResponse) {
        PayloadValidatingInterceptor validator = new PayloadValidatingInterceptor();
        validator.setSchema(schemaPath);
        validator.setValidateRequest(validateRequest);
        validator.setValidateResponse(validateResponse);
        return validator;
    }
}
