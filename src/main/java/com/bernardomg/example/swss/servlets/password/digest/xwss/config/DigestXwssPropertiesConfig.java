package com.bernardomg.example.swss.servlets.password.digest.xwss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class DigestXwssPropertiesConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(
			ResourceLoader loader) {
		PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertyConfigurer.setLocations(
				loader.getResource("classpath:config/endpoint/endpoint.properties"),
				loader.getResource(
						"classpath:config/interceptor/password/digest/xwss/interceptor-password-digest-xwss.properties"),
                loader.getResource(
                        "classpath:config/endpoint/password/digest/xwss/endpoint-password-digest-xwss.properties"));
		return propertyConfigurer;
	}

	
}