package com.bernardomg.example.swss.servlet.encryption.xwss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class EncryptionXwssPropertiesConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(
			ResourceLoader loader) {
		PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertyConfigurer.setLocations(
				loader.getResource("classpath:config/keystore/keystore.properties"),
				loader.getResource("classpath:config/endpoint/endpoint.properties"),
				loader.getResource(
						"classpath:config/interceptor/encryption/xwss/interceptor-encryption-xwss.properties"),
                loader.getResource(
                        "classpath:config/endpoint/encryption/xwss/endpoint-encryption-xwss.properties"));
		return propertyConfigurer;
	}

	
}