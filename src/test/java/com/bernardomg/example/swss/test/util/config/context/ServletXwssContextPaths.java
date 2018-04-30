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

import java.util.Arrays;
import java.util.List;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SpringPlainTextPasswordValidationCallbackHandler;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor;

import com.bernardomg.example.swss.common.WsdlConfig;
import com.bernardomg.example.swss.password.digest.xwss.config.DigestXwssPropertiesConfig;
import com.bernardomg.example.swss.password.plain.xwss.config.PlainXwssPropertiesConfig;
import com.bernardomg.example.swss.test.util.factory.WebServiceMockFactory;
import com.sun.xml.wss.impl.callback.PasswordValidationCallback.PasswordValidationException;

/**
 * Paths to the XWSS-based servlet context files.
 * <p>
 * Each of these files can be used to create a Spring context for a single
 * servlet, using XWSS interceptors to handle security.
 * <p>
 * These are the same context configurations as the ones used for the
 * application servlets.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class ServletXwssContextPaths {

    /**
     * Encrypted servlet.
     */
    public static final String ENCRYPTION             = "classpath:context/servlet/encryption/xwss/servlet-encryption-xwss.xml";

    /**
     * Digested password servlet.
     */
    public static final String PASSWORD_DIGEST        = "classpath:context/servlet/password/digest/xwss/servlet-password-digest-xwss.xml";

    /**
     * Digested password servlet with mocked dependencies.
     */
    public static final String PASSWORD_DIGEST_MOCKED = "classpath:context/servlet/password/digest/xwss/test-servlet-password-digest-xwss.xml";

    /**
     * Plain password servlet.
     */
    public static final String PASSWORD_PLAIN         = "classpath:context/servlet/password/plain/xwss/servlet-password-plain-xwss.xml";

    /**
     * Plain password servlet with mocked dependencies.
     */
    public static final String PASSWORD_PLAIN_MOCKED  = "classpath:context/servlet/password/plain/xwss/test-servlet-password-plain-xwss.xml";

    /**
     * Signed servlet.
     */
    public static final String SIGNATURE              = "classpath:context/servlet/signature/xwss/servlet-signature-xwss.xml";

    /**
     * Unsecure servlet.
     */
    public static final String UNSECURE               = "classpath:context/servlet/servlet-unsecure.xml";

    /**
     * Private constructor to avoid initialization.
     */
    private ServletXwssContextPaths() {
        super();
    }
    
    public static class CommonTestServletPasswordXwss {
    	@Configuration
    	public static class WSInterceptorConfig {

    		@Bean
    		public XwsSecurityInterceptor securityInterceptor(
    				@Value("${security.file.path}") Resource securityFilePath,
    				CallbackHandler validationHandler) {
    			XwsSecurityInterceptor interceptor = new XwsSecurityInterceptor();
    			interceptor.setPolicyConfiguration(securityFilePath);
    			interceptor.setCallbackHandlers(new CallbackHandler[] { validationHandler });
    			return interceptor;
    		}

    		@Bean
    		public CallbackHandler validationHandler(WebServiceMockFactory mocksFactory) throws Exception {
    			return mocksFactory.getValidationCallbackHandler();
    		}
    	}
    	
    	@Configuration
    	@EnableWs
    	public class WSConfig extends WsConfigurerAdapter {

    	    @Autowired
    	    private XwsSecurityInterceptor securityInterceptor;
    	    
    	    @Override
    	    public void addInterceptors(List<EndpointInterceptor> interceptors) {
    	        interceptors.add(securityInterceptor);
    	    }
    	}
    }
    
    @Configuration
    @Import({ DigestXwssPropertiesConfig.class, WsdlConfig.class })
    @ComponentScan("com.bernardomg.example.swss.endpoint")
    public static class TestServletPasswordDigestXwss extends CommonTestServletPasswordXwss {
    }

    @Configuration
    @Import({ PlainXwssPropertiesConfig.class, WsdlConfig.class })
    @ComponentScan("com.bernardomg.example.swss.endpoint")
    public static class TestServletPasswordPlainXwss extends CommonTestServletPasswordXwss {
    }
}