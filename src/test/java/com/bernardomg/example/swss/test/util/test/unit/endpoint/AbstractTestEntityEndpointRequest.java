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

package com.bernardomg.example.swss.test.util.test.unit.endpoint;

import javax.xml.transform.Source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatcher;
import org.springframework.ws.test.server.ResponseMatchers;

import com.bernardomg.example.swss.test.util.config.properties.TestPropertiesPaths;
import com.bernardomg.example.swss.test.util.factory.SoapActionRequestCreators;

/**
 * Abstract unit tests for an endpoint testing that it handles envelope-based
 * SOAP messages correctly.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>The endpoint parses SOAP requests with a valid envelope.</li>
 * <li>The endpoint can handle invalid SOAP messages.</li>
 * </ol>
 * <p>
 * This base test is meant for those endpoints where the full envelope is more
 * important than the payload, which is the case of the secured endpoints.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@TestPropertySource({ TestPropertiesPaths.USER })
public abstract class AbstractTestEntityEndpointRequest
        extends AbstractTestEndpoint {

    /**
     * Application context to be used for creating the client mock.
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Path to XSD file which validates the SOAP messages.
     */
    @Value("${xsd.entity.path}")
    private String             entityXsdPath;

    /**
     * Path to the file with the invalid request payload.
     */
    @Value("${soap.request.invalid.path}")
    private String             requestEnvelopeInvalidPath;

    /**
     * SOAP action for the tested message.
     */
    @Value("${endpoint.action}")
    private String             soapAction;

    /**
     * Default constructor.
     */
    public AbstractTestEntityEndpointRequest() {
        super();
        // TODO: The endpoint dependencies, mostly the service, should be mocked
    }

    /**
     * Tests that the endpoint parses SOAP requests with a valid envelope.
     */
    @Test
    public final void testEndpoint_Envelope_Valid() throws Exception {
        final MockWebServiceClient mockClient; // Mocked client
        final RequestCreator requestCreator; // Creator for the request
        final ResponseMatcher responseMatcher; // Matcher for the response

        // Creates the request
        requestCreator = SoapActionRequestCreators.withSoapEnvelope(soapAction,
                getRequestEnvelope());

        // Creates the response matcher
        responseMatcher = ResponseMatchers
                .validPayload(new ClassPathResource(entityXsdPath));

        // Creates the client mock
        mockClient = MockWebServiceClient.createClient(applicationContext);

        // Calls the endpoint
        mockClient.sendRequest(requestCreator).andExpect(responseMatcher);
    }

    /**
     * Tests that the endpoint can handle invalid SOAP messages.
     */
    @Test
    public final void testEndpoint_Invalid() throws Exception {
        final MockWebServiceClient mockClient; // Mocked client
        final RequestCreator requestCreator; // Creator for the request
        final ResponseMatcher responseMatcher; // Matcher for the response

        // Creates the request
        requestCreator = RequestCreators.withPayload(
                new ClassPathResource(requestEnvelopeInvalidPath));

        // Creates the response matcher
        responseMatcher = ResponseMatchers.clientOrSenderFault();

        // Creates the client mock
        mockClient = MockWebServiceClient.createClient(applicationContext);

        // Calls the endpoint
        mockClient.sendRequest(requestCreator).andExpect(responseMatcher);
    }

    /**
     * Returns a valid SOAP request envelope in a {@code Source} class.
     *
     * @return a valid SOAP request envelope
     */
    protected abstract Source getRequestEnvelope();

}
