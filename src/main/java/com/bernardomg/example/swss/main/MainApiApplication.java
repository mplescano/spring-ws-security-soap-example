package com.bernardomg.example.swss.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration;

/**
 * Look at https://spring.io/guides/gs/producing-web-service/
 * @author mplescano
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = { WebServicesAutoConfiguration.class, DispatcherServletAutoConfiguration.class, 
        JmxAutoConfiguration.class, WebMvcAutoConfiguration.class })
public class MainApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApiApplication.class, args);
    }
}
