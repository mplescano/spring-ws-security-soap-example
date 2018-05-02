package com.bernardomg.example.swss.servlet.signature.xwss.config;

import java.security.KeyStore;
import java.util.Arrays;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ws.soap.security.x509.X509AuthenticationProvider;
import org.springframework.ws.soap.security.x509.populator.DaoX509AuthoritiesPopulator;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.KeyStoreCallbackHandler;
import org.springframework.ws.soap.security.xwss.callback.SpringCertificateValidationCallbackHandler;

@Configuration
public class SignatureXwssInterceptorConfig {

	@Bean
	public XwsSecurityInterceptor securityInterceptor(
			@Value("${security.file.path}") Resource securityFilePath,
			@Qualifier("validationHandler") CallbackHandler validationHandler) {
		XwsSecurityInterceptor interceptor = new XwsSecurityInterceptor();
		interceptor.setPolicyConfiguration(securityFilePath);
		interceptor.setCallbackHandlers(new CallbackHandler[] { validationHandler });
		return interceptor;
	}

	@Bean
	public CallbackHandler validationHandler(@Qualifier("keyStore") KeyStore keyStore, 
	                                         @Qualifier("trustStore") KeyStore trustStore,
	                                         @Value("${keystore.password}") String keystorePassword,
	                                         @Value("${keystore.alias}") String keystoreAlias) {
	    KeyStoreCallbackHandler validationHandler = new KeyStoreCallbackHandler();
		validationHandler.setKeyStore(keyStore);
		validationHandler.setTrustStore(trustStore);
		validationHandler.setPrivateKeyPassword(keystorePassword);
		validationHandler.setDefaultAlias(keystoreAlias);
		return validationHandler;
	}
	
	/*
	public SpringCertificateValidationCallbackHandler certificateHandler(AuthenticationManager authenticationManager) {
	    SpringCertificateValidationCallbackHandler certificateHandler = new SpringCertificateValidationCallbackHandler();
	    certificateHandler.setAuthenticationManager(authenticationManager);
	    return certificateHandler;
	}
	
	@Bean
	public ProviderManager authenticationManager(UserDetailsService userDetailsService) {
		DaoX509AuthoritiesPopulator authPopulator = new DaoX509AuthoritiesPopulator();
		authPopulator.setUserDetailsService(userDetailsService);
		X509AuthenticationProvider x509Provider = new X509AuthenticationProvider();
		x509Provider.setX509AuthoritiesPopulator(authPopulator);
		ProviderManager authenticationManager = new ProviderManager(
				Arrays.asList(x509Provider));
		return authenticationManager;
	}*/
}