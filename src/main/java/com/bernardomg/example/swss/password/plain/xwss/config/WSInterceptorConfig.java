package com.bernardomg.example.swss.password.plain.xwss.config;

import java.util.Arrays;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SpringPlainTextPasswordValidationCallbackHandler;

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
	public CallbackHandler validationHandler(
			AuthenticationManager authenticationManager) {
		SpringPlainTextPasswordValidationCallbackHandler validationHandler = new SpringPlainTextPasswordValidationCallbackHandler();
		validationHandler.setAuthenticationManager(authenticationManager);
		return validationHandler;
	}

	@Bean
	public ProviderManager authenticationManager(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(userDetailsService);
		ProviderManager authenticationManager = new ProviderManager(
				Arrays.asList(daoProvider));
		return authenticationManager;
	}
}