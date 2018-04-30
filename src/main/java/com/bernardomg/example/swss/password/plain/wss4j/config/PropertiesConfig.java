package com.bernardomg.example.swss.password.plain.wss4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class PropertiesConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(
			ResourceLoader loader) {
		PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertyConfigurer.setLocations(
				loader.getResource("classpath:config/endpoint/endpoint.properties"),
				loader.getResource(
						"classpath:config/interceptor/password/plain/wss4j/interceptor-password-plain-wss4j.properties"),
                loader.getResource(
                        "classpath:config/endpoint/password/plain/wss4j/endpoint-password-plain-wss4j.properties"));
		return propertyConfigurer;
	}

	
}
