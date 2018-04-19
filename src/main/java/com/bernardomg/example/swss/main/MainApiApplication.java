package com.bernardomg.example.swss.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;

/**
 * Look at https://spring.io/guides/gs/producing-web-service/
 * @author mplescano
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = { DispatcherServletAutoConfiguration.class, JmxAutoConfiguration.class })
public class MainApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApiApplication.class, args);
    }
}
