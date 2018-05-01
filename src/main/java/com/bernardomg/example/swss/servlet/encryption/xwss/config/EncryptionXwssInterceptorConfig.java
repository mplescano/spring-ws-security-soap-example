package com.bernardomg.example.swss.servlet.encryption.xwss.config;

import java.security.KeyStore;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.KeyStoreCallbackHandler;

@Configuration
public class EncryptionXwssInterceptorConfig {
    
	@Bean
	public XwsSecurityInterceptor securityInterceptor(
			@Value("${security.file.path}") Resource securityFilePath,
			CallbackHandler validationHandler) {
		XwsSecurityInterceptor interceptor = new XwsSecurityInterceptor();
		interceptor.setPolicyConfiguration(securityFilePath);
		interceptor.setCallbackHandlers(new CallbackHandler[] { validationHandler });
		return interceptor;
	}

	@Bean
	public CallbackHandler validationHandler(@Qualifier("keyStore") KeyStore keyStore, @Qualifier("trustStore") KeyStore trustStore, 
	                                         @Qualifier("symmetricStore") KeyStore symmetricStore,  
	                                         @Value("${symmetricstore.password}") String symmetricstorePassword,
	                                         @Value("${symmetricstore.alias}") String symmetricstoreAlias) {
	    KeyStoreCallbackHandler validationHandler = new KeyStoreCallbackHandler();
		validationHandler.setKeyStore(keyStore);
		validationHandler.setTrustStore(trustStore);
		validationHandler.setSymmetricStore(symmetricStore);
		validationHandler.setPrivateKeyPassword(symmetricstorePassword);
		validationHandler.setSymmetricKeyPassword(symmetricstorePassword);
		validationHandler.setDefaultAlias(symmetricstoreAlias);
		return validationHandler;
	}
}