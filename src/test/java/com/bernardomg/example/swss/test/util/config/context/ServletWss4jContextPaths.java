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

import java.util.List;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import com.bernardomg.example.swss.servlets.common.WsdlConfig;
import com.bernardomg.example.swss.servlets.password.digest.wss4j.config.DigestWss4jPropertiesConfig;
import com.bernardomg.example.swss.servlets.password.plain.wss4j.config.PlainWss4jPropertiesConfig;
import com.bernardomg.example.swss.test.util.factory.WebServiceMockFactory;

/**
 * Paths to the WSS4J-based servlet context files.
 * <p>
 * Each of these files can be used to create a Spring context for a single
 * servlet, using WSS4J interceptors to handle security.
 * <p>
 * These are the same context configurations as the ones used for the
 * application servlets.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class ServletWss4jContextPaths {

    /**
     * Encrypted servlet.
     */
    public static final String ENCRYPTION             = "classpath:context/servlet/encryption/wss4j/servlet-encryption-wss4j.xml";

    /**
     * Digested password servlet.
     */
    public static final String PASSWORD_DIGEST        = "classpath:context/servlet/password/digest/wss4j/servlet-password-digest-wss4j.xml";

    /**
     * Digested password servlet with mocked dependencies.
     */
    public static final String PASSWORD_DIGEST_MOCKED = "classpath:context/servlet/password/digest/wss4j/test-servlet-password-digest-wss4j.xml";

    /**
     * Plain password servlet.
     */
    public static final String PASSWORD_PLAIN         = "classpath:context/servlet/password/plain/wss4j/servlet-password-plain-wss4j.xml";

    /**
     * Plain password servlet with mocked dependencies.
     */
    public static final String PASSWORD_PLAIN_MOCKED  = "classpath:context/servlet/password/plain/wss4j/test-servlet-password-plain-wss4j.xml";

    /**
     * Signed servlet.
     */
    public static final String SIGNATURE              = "classpath:context/servlet/signature/wss4j/servlet-signature-wss4j.xml";

    /**
     * Unsecure servlet.
     */
    public static final String UNSECURE               = "classpath:context/servlet/servlet-unsecure.xml";

    /**
     * Private constructor to avoid initialization.
     */
    private ServletWss4jContextPaths() {
        super();
    }

    @Configuration
    @Import({ DigestWss4jPropertiesConfig.class, WsdlConfig.class })
    @ComponentScan("com.bernardomg.example.swss.endpoint")
    public static class TestServletPasswordDigestWss4j extends CommonTestServletPasswordWss4j {
    }
    
    @Configuration
    @Import({ PlainWss4jPropertiesConfig.class, WsdlConfig.class })
    @ComponentScan("com.bernardomg.example.swss.endpoint")
    public static class TestServletPasswordPlainWss4j extends CommonTestServletPasswordWss4j {
    }
    
    public static class CommonTestServletPasswordWss4j {
    	
        @Configuration
        @EnableWs
        public static class WSConfig extends WsConfigurerAdapter {

            @Autowired
            private Wss4jSecurityInterceptor wss4jSecurityInterceptor;
            
            @Override
            public void addInterceptors(List<EndpointInterceptor> interceptors) {
                interceptors.add(wss4jSecurityInterceptor);
            }
        }
        
        @Configuration
        public static class WSInterceptorConfig {

            @Bean
            public Wss4jSecurityInterceptor securityInterceptor(@Value("${security.actions}") String securityActions,
                                                                @Value("${security.credentials.user}") String securityCredentialsUser,
                                                                @Value("${security.credentials.password}") String securityCredentialsPassword,
                                                                @Value("${security.credentials.password.type}") String securityCredentialsPasswordType,
                                                                @Qualifier("validationHandler") CallbackHandler validationHandler) {
                Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
                interceptor.setSecurementActions(securityActions);
                interceptor.setSecurementUsername(securityCredentialsUser);
                interceptor.setSecurementPassword(securityCredentialsPassword);
                interceptor.setSecurementPasswordType(securityCredentialsPasswordType);
                interceptor.setValidationActions(securityActions);
                interceptor.setValidationCallbackHandler(validationHandler);
                interceptor.setSecureResponse(false);
                interceptor.setValidateResponse(false);
                return interceptor;
            }
            
            @Bean
            public CallbackHandler validationHandler(@Qualifier("mocksFactory") WebServiceMockFactory mocksFactory) throws Exception {
                return mocksFactory.getValidationCallbackHandler();
            }
        }
    }

}
