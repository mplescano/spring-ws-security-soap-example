package com.bernardomg.example.swss.servlet.signature.xwss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class SignatureXwssPropertiesConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(
			ResourceLoader loader) {
		PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertyConfigurer.setLocations(
				loader.getResource("classpath:config/keystore/keystore.properties"),
				loader.getResource("classpath:config/endpoint/endpoint.properties"),
				loader.getResource(
						"classpath:config/interceptor/signature/xwss/interceptor-signature-xwss.properties"),
                loader.getResource(
                        "classpath:config/endpoint/signature/xwss/endpoint-signature-xwss.properties"));
		return propertyConfigurer;
	}

	
}