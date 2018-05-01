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

package com.bernardomg.example.swss.test.unit.endpoint.encryption.xwss;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.bernardomg.example.swss.main.config.KeyStoreConfig;
import com.bernardomg.example.swss.servlet.encryption.xwss.WSEncryptionXwssContext;
import com.bernardomg.example.swss.test.util.config.properties.EndpointXwssPropertiesPaths;
import com.bernardomg.example.swss.test.util.config.properties.InterceptorXwssPropertiesPaths;
import com.bernardomg.example.swss.test.util.config.properties.SoapPropertiesPaths;
import com.bernardomg.example.swss.test.util.config.properties.TestEndpointXwssPropertiesPaths;
import com.bernardomg.example.swss.test.util.config.properties.TestPropertiesPaths;
import com.bernardomg.example.swss.test.util.test.unit.endpoint.AbstractTestEntityEndpointRequest;

/**
 * Unit test for a XWSS encrypted endpoint.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@ContextConfiguration(classes = { WSEncryptionXwssContext.class, KeyStoreConfig.class }, 
    loader = AnnotationConfigContextLoader.class)
@TestPropertySource({ SoapPropertiesPaths.UNSECURE,
        SoapPropertiesPaths.ENCRYPTION_XWSS,
        InterceptorXwssPropertiesPaths.ENCRYPTION,
        EndpointXwssPropertiesPaths.ENCRYPTION,
        TestEndpointXwssPropertiesPaths.ENCRYPTION,
        TestPropertiesPaths.KEYSTORE })
public final class TestEntityEndpointRequestEncryptionXwss
        extends AbstractTestEntityEndpointRequest {

    /**
     * Path to the file containing the valid SOAP request.
     */
    @Value("${soap.request.path}")
    private String pathValid;

    /**
     * Constructs a {@code TestEntityEndpointEncryptionXWSS}.
     */
    public TestEntityEndpointRequestEncryptionXwss() {
        super();
    }

    @Override
    protected final Source getRequestEnvelope() {
        try {
            return new StreamSource(
                    new ClassPathResource(pathValid).getInputStream());
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
