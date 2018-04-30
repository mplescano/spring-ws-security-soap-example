package com.bernardomg.example.swss.unsecure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class UnsecurePropertiesConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(
			ResourceLoader loader) {
		PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertyConfigurer.setLocations(
				loader.getResource("classpath:config/endpoint/endpoint.properties"),
				loader.getResource(
						"classpath:config/endpoint/endpoint-unsecure.properties"));
		return propertyConfigurer;
	}

	
}
