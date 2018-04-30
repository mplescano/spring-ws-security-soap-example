package com.bernardomg.example.swss.servlet.signature.xwss.config;

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

	/*
   <!-- Security interceptor -->
   <bean id="securityInterceptor" class="${interceptor.security.class}">
      <property name="policyConfiguration" value="${security.file.path}" />
      <property name="callbackHandlers">
         <list>
            <ref bean="keystoreCallbackHandler" />
            <!-- <ref bean="certificateHandler" /> -->
         </list>
      </property>
   </bean>

   <!-- Key store callback handler -->
   <bean id="keystoreCallbackHandler" class="${callbackHandler.validation.keystore.class}">
      <property name="keyStore" ref="keyStore" />
      <property name="trustStore" ref="trustStore" />
      <property name="privateKeyPassword" value="${keystore.password}" />
      <property name="defaultAlias" value="${keystore.alias}" />
   </bean>

   <!-- Certificate callback handler -->
   <bean id="certificateHandler" class="${callbackHandler.validation.certificate.class}">
      <property name="authenticationManager" ref="authenticationManager" />
   </bean>

   <!-- Authentication Manager -->
   <bean id="authenticationManager" class="${authentication.manager.class}">
      <constructor-arg>
         <list>
            <ref bean="authenticationProvider" />
         </list>
      </constructor-arg>
   </bean>

   <!-- Authentication Provider -->
   <bean id="authenticationProvider" class="${authentication.provider.class}">
      <property name="x509AuthoritiesPopulator" ref="authPopulator" />
   </bean>

   <!-- X509 authorities populator -->
   <bean id="authPopulator" class="${authentication.provider.populator.class}">
      <property name="userDetailsService" ref="userDetailsService" />
   </bean>
	 * */
	
	
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