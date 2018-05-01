package com.bernardomg.example.swss.servlet.encryption.wss4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class EncryptionWss4jPropertiesConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(
			ResourceLoader loader) {
		PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertyConfigurer.setLocations(
				loader.getResource("classpath:config/keystore/keystore.properties"),
				loader.getResource("classpath:config/endpoint/endpoint.properties"),
				loader.getResource(
						"classpath:config/interceptor/encryption/wss4j/interceptor-encryption-wss4j.properties"),
                loader.getResource(
                        "classpath:config/endpoint/encryption/wss4j/endpoint-encryption-wss4j.properties"));
		return propertyConfigurer;
	}

	
}