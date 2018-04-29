package com.bernardomg.example.swss.main.config;

import org.apache.wss4j.common.crypto.Crypto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

@Configuration
public class Wss4jCryptoConfig {

    @Bean
    public Crypto keyStoreWSS4J(@Value("${keystore.path}") Resource keystorePath,
                                        @Value("${keystore.password}") String keystorePassword) throws Exception {
        CryptoFactoryBean factory = new CryptoFactoryBean();
        factory.setKeyStorePassword(keystorePassword);
        factory.setKeyStoreLocation(keystorePath);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
	
    @Bean
    public Crypto symmetricStoreWSS4J(@Value("${symmetricstore.path}") Resource symmetricstorePath,
                                      @Value("${symmetricstore.password}") String symmetricstorePassword,
                                      @Value("${symmetricstore.type}") String symmetricstoreType) throws Exception {
        CryptoFactoryBean factory = new CryptoFactoryBean();
        factory.setKeyStorePassword(symmetricstorePassword);
        factory.setKeyStoreLocation(symmetricstorePath);
        factory.setKeyStoreType(symmetricstoreType);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

}