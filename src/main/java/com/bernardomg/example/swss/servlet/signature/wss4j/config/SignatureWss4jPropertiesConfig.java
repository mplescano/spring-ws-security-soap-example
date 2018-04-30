package com.bernardomg.example.swss.servlet.signature.wss4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class SignatureWss4jPropertiesConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(
			ResourceLoader loader) {
		PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertyConfigurer.setLocations(
				loader.getResource("classpath:config/keystore/keystore.properties"),
				loader.getResource("classpath:config/endpoint/endpoint.properties"),
				loader.getResource(
						"classpath:config/interceptor/signature/wss4j/interceptor-signature-wss4j.properties"),
                loader.getResource(
                        "classpath:config/endpoint/signature/wss4j/endpoint-signature-wss4j.properties"));
		return propertyConfigurer;
	}

	
}