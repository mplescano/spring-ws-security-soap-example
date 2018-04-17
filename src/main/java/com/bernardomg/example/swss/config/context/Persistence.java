package com.bernardomg.example.swss.config.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;

@Configuration
public class Persistence {

	
    @Bean
    public JpaVendorAdapter jpaVendorAdaper(@Value("${jpa.adapter.class}") String classJpaAdapter, 
    		@Value("${jpa.database}") Database jpaDatabase, @Value("${jpa.showSql}") boolean jpaShowSql) throws Exception {
    	AbstractJpaVendorAdapter vendorAdapter = (AbstractJpaVendorAdapter) Class.forName(classJpaAdapter).newInstance();
    	vendorAdapter.setDatabase(jpaDatabase);
    	vendorAdapter.setShowSql(jpaShowSql);
        return vendorAdapter;
    }
	
}