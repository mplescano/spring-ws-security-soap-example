package com.bernardomg.example.swss.main.config;

import java.security.KeyStore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.ws.soap.security.support.KeyStoreFactoryBean;

@Configuration
public class KeyStoreConfig {

    @Bean
    public KeyStore keyStore(@Value("${keystore.path}") Resource keystorePath,
                                        @Value("${keystore.password}") String keystorePassword) throws Exception {
        KeyStoreFactoryBean factory = new KeyStoreFactoryBean();
        factory.setPassword(keystorePassword);
        factory.setLocation(keystorePath);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
	
    @Bean
    public KeyStore trustStore(@Value("${truststore.path}") Resource truststorePath,
                                          @Value("${truststore.password}") String truststorePassword) throws Exception {
        KeyStoreFactoryBean factory = new KeyStoreFactoryBean();
        factory.setPassword(truststorePassword);
        factory.setLocation(truststorePath);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
    
    @Bean
    public KeyStore symmetricStore(@Value("${symmetricstore.path}") Resource symmetricstorePath,
                                              @Value("${symmetricstore.password}") String symmetricstorePassword,
                                              @Value("${symmetricstore.type}") String symmetricstoreType) throws Exception {
        KeyStoreFactoryBean factory = new KeyStoreFactoryBean();
        factory.setPassword(symmetricstorePassword);
        factory.setLocation(symmetricstorePath);
        factory.setType(symmetricstoreType);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}