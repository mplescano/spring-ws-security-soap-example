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

package com.bernardomg.example.swss.test.unit.endpoint.encryption.wss4j;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.bernardomg.example.swss.main.config.KeyStoreConfig;
import com.bernardomg.example.swss.main.config.Wss4jCryptoConfig;
import com.bernardomg.example.swss.servlet.encryption.wss4j.WSEncryptionWss4jContext;
import com.bernardomg.example.swss.test.util.config.properties.EndpointWss4jPropertiesPaths;
import com.bernardomg.example.swss.test.util.config.properties.InterceptorWss4jPropertiesPaths;
import com.bernardomg.example.swss.test.util.config.properties.SoapPropertiesPaths;
import com.bernardomg.example.swss.test.util.config.properties.TestEndpointWss4jPropertiesPaths;
import com.bernardomg.example.swss.test.util.config.properties.TestPropertiesPaths;
import com.bernardomg.example.swss.test.util.test.unit.endpoint.AbstractTestEntityEndpointRequest;

/**
 * Unit test for a XWSS encrypted endpoint.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@ContextConfiguration(classes = { WSEncryptionWss4jContext.class, KeyStoreConfig.class, Wss4jCryptoConfig.class }, 
    loader = AnnotationConfigContextLoader.class)
@TestPropertySource({ SoapPropertiesPaths.UNSECURE,
        SoapPropertiesPaths.ENCRYPTION_WSS4J,
        InterceptorWss4jPropertiesPaths.ENCRYPTION,
        EndpointWss4jPropertiesPaths.ENCRYPTION, TestPropertiesPaths.KEYSTORE,
        TestPropertiesPaths.KEYSTORE_WSS4J,
        TestEndpointWss4jPropertiesPaths.ENCRYPTION })
public final class TestEntityEndpointRequestEncryptionWss4j
        extends AbstractTestEntityEndpointRequest {

    /**
     * Path to the file containing the valid SOAP request.
     */
    @Value("${soap.request.path}")
    private String pathValid;

    /**
     * Constructs a {@code TestEntityEndpointEncryptionWSS4J}.
     */
    public TestEntityEndpointRequestEncryptionWss4j() {
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
