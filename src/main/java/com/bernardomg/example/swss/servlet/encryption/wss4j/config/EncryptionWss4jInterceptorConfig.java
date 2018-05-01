package com.bernardomg.example.swss.servlet.encryption.wss4j.config;

import java.security.KeyStore;

import javax.security.auth.callback.CallbackHandler;

import org.apache.wss4j.common.crypto.Crypto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.KeyStoreCallbackHandler;

@Configuration
public class EncryptionWss4jInterceptorConfig {
    
    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(@Value("${security.actions}") String securityActions,
                                                        @Value("${keystore.alias}") String keystoreAlias,
                                                        @Qualifier("keyStoreWSS4J") Crypto keyStoreWSS4J,
                                                        @Value("${security.encryptionKeyIdentifier}") String securityEncryptionKeyIdentifier,
                                                        @Qualifier("validationHandler") CallbackHandler validationHandler) {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementActions(securityActions);
        interceptor.setSecurementEncryptionUser(keystoreAlias);
        interceptor.setSecurementSignatureCrypto(keyStoreWSS4J);
        interceptor.setSecurementEncryptionKeyIdentifier(securityEncryptionKeyIdentifier);
        interceptor.setValidationActions(securityActions);
        interceptor.setValidationCallbackHandler(validationHandler);
        interceptor.setValidationDecryptionCrypto(keyStoreWSS4J);
        interceptor.setSecureResponse(false);
        interceptor.setValidateResponse(false);
        return interceptor;
    }
    
    @Bean
    public CallbackHandler validationHandler(@Qualifier("keyStore") KeyStore keyStore,
                                             @Value("${keystore.password}") String keystorePassword) {
    	KeyStoreCallbackHandler validationHandler = new KeyStoreCallbackHandler();
    	validationHandler.setKeyStore(keyStore);
        validationHandler.setPrivateKeyPassword(keystorePassword);
        return validationHandler;
    }
}