package com.bernardomg.example.swss.servlet.signature.wss4j.config;

import javax.security.auth.callback.CallbackHandler;

import org.apache.wss4j.common.crypto.Crypto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.KeyStoreCallbackHandler;

@Configuration
public class SignatureWss4jInterceptorConfig {

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(@Value("${security.actions}") String securityActions,
                                                        @Value("${keystore.alias}") String keystoreAlias,
                                                        @Value("${keystore.password}") String keystorePassword,
                                                        @Qualifier("keyStoreWSS4J") Crypto keyStoreWSS4J,
                                                        @Qualifier("validationHandler") CallbackHandler validationHandler) {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementActions(securityActions);
        interceptor.setSecurementUsername(keystoreAlias);
        interceptor.setSecurementPassword(keystorePassword);
        interceptor.setSecurementSignatureCrypto(keyStoreWSS4J);
        interceptor.setValidationActions(securityActions);
        interceptor.setValidationSignatureCrypto(keyStoreWSS4J);
        interceptor.setValidationCallbackHandler(validationHandler);
        interceptor.setSecureResponse(false);
        interceptor.setValidateResponse(false);
        return interceptor;
    }
    
    @Bean
    public CallbackHandler validationHandler(@Value("${keystore.password}") String keystorePassword) {
    	KeyStoreCallbackHandler validationHandler = new KeyStoreCallbackHandler();
        validationHandler.setPrivateKeyPassword(keystorePassword);
        return validationHandler;
    }
}