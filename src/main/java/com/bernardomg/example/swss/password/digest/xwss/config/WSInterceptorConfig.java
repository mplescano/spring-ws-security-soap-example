package com.bernardomg.example.swss.password.digest.xwss.config;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SpringDigestPasswordValidationCallbackHandler;

@Configuration
public class WSInterceptorConfig {

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
	public CallbackHandler validationHandler(UserDetailsService userDetailsService) {
		SpringDigestPasswordValidationCallbackHandler validationHandler = new SpringDigestPasswordValidationCallbackHandler();
		validationHandler.setUserDetailsService(userDetailsService);
		return validationHandler;
	}
}