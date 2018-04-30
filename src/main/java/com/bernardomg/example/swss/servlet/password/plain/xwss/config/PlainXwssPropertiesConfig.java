package com.bernardomg.example.swss.servlet.password.plain.xwss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class PlainXwssPropertiesConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(
			ResourceLoader loader) {
		PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertyConfigurer.setLocations(
				loader.getResource("classpath:config/endpoint/endpoint.properties"),
				loader.getResource(
						"classpath:config/interceptor/password/plain/xwss/interceptor-password-plain-xwss.properties"),
                loader.getResource(
                        "classpath:config/endpoint/password/plain/xwss/endpoint-password-plain-xwss.properties"));
		return propertyConfigurer;
	}

	
}
