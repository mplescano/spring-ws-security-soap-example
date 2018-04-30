/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2017 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.example.swss.test.util.config.context;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;

import com.bernardomg.example.swss.main.service.ExampleEntityService;
import com.bernardomg.example.swss.test.util.factory.WebServiceMockFactory;

/**
 * Paths to the servlet context files.
 * <p>
 * These are the context files shared by all the servlets, not matter their type.
 * <p>
 * These are the same context configurations as the ones used for the application servlets.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class ServletContextPaths {

    /**
     * Application-wide context configuration.
     * <p>
     * This is the application context used for real web services, and shared by all the servlets.
     */
    public static final String APPLICATION = "classpath:context/web-service.xml";

    /**
     * Mocked application context configuration.
     * <p>
     * This is an application context using mocked dependencies.
     */
    public static final String APPLICATION_MOCKED = "classpath:context/test-web-service.xml";

    /**
     * Private constructor to avoid initialization.
     */
    private ServletContextPaths() {
        super();
    }

    @Configuration
    public static class TestWebService {

        @Bean
        public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(ResourceLoader loader) {
            PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
            propertyConfigurer.setLocations(loader.getResource("classpath:config/test-web-service.properties"));
            return propertyConfigurer;
        }

        @Bean
        public WebServiceMockFactory mocksFactory() throws Exception {
            return new WebServiceMockFactory();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public ExampleEntityService exampleEntityService(@Qualifier("mocksFactory") WebServiceMockFactory mocksFactory)
                throws Exception {
            return mocksFactory.getExampleEntityService();
        }
    }
}
