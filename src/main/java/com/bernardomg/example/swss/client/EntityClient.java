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

package com.bernardomg.example.swss.client;

import com.bernardomg.example.swss.main.model.ExampleEntity;

/**
 * Client for querying the web service endpoints.
 * <p>
 * It supports the only operation which the
 * {@link com.bernardomg.example.swss.endpoint.ExampleEntityEndpoint
 * ExampleEntityEndpoint} has: querying an entity by its id.
 * <p>
 * Implementations are expected to take care of any security protocol used by
 * the queried endpoint. After all this example is about web service security.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 * @see ExampleEntity
 */
public interface EntityClient {

    /**
     * Sends an id to the endpoint and receives back the entity with that same
     * id.
     * <p>
     * The method is expected to never return a null. Instead it should return
     * an entity with a negative id. This may happen for example if the response
     * was empty, or the id was not for a valid entity.
     *
     * @param uri
     *            URI to the endpoint
     * @param identifier
     *            id of the queried entity
     * @return the entity for the given id
     */
    public ExampleEntity getEntity(final String uri, final Integer identifier);

}
