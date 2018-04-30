package com.bernardomg.example.swss.password.plain.wss4j.config;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SpringSecurityPasswordValidationCallbackHandler;

@Configuration
public class WSInterceptorConfig {

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(@Value("${security.actions}") String securityActions,
                                                        @Value("${security.credentials.user}") String securityCredentialsUser,
                                                        @Value("${security.credentials.password}") String securityCredentialsPassword,
                                                        @Value("${security.credentials.password.type}") String securityCredentialsPasswordType,
                                                        @Qualifier("validationHandler") CallbackHandler validationHandler) {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementActions(securityActions);
        interceptor.setSecurementUsername(securityCredentialsUser);
        interceptor.setSecurementPassword(securityCredentialsPassword);
        interceptor.setSecurementPasswordType(securityCredentialsPasswordType);
        interceptor.setValidationActions(securityActions);
        interceptor.setValidationCallbackHandler(validationHandler);
        interceptor.setSecureResponse(false);
        interceptor.setValidateResponse(false);
        return interceptor;
    }
    
    @Bean
    public SpringSecurityPasswordValidationCallbackHandler validationHandler(UserDetailsService userDetailsService) {
        SpringSecurityPasswordValidationCallbackHandler validationHandler = new SpringSecurityPasswordValidationCallbackHandler();
        validationHandler.setUserDetailsService(userDetailsService);
        return validationHandler;
    }
}