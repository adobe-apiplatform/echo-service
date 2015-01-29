/*******************************************************************************
 * ADOBE CONFIDENTIAL
 * ___________________
 *
 *  Copyright 2014 Adobe Systems Incorporated
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by all applicable intellectual property
 * laws, including trade secret and copyright laws.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 ******************************************************************************/

package com.adobe.api.platform.echo.test;

import com.adobe.api.platform.msc.test.BaseTest;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Cristian Constantin
 * @since 11/20/14
 */
public class IntegrationTest extends BaseTest {

    @Test
    public void testEchoBody() {

        String requestBody = "hello";
        Response response = getRestClient()
                .path("echo")
                .contentType(MediaType.TEXT_PLAIN_TYPE)
                .acceptedMediaTypes(MediaType.TEXT_PLAIN_TYPE)
                .post(Response.class, requestBody);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(requestBody, response.readEntity(String.class));
    }

    @Test
    public void testEchoBodyValidation() {

        Response response = getRestClient()
                .path("echo")
                .contentType(MediaType.TEXT_PLAIN_TYPE)
                .acceptedMediaTypes(MediaType.TEXT_PLAIN_TYPE)
                .post(Response.class, "");

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testEchoHeaders() {

        Response response = getRestClient()
                .path("echo").path("headers")
                .acceptedMediaTypes(MediaType.TEXT_PLAIN_TYPE)
                .header("X-Custom-Header", "0000")
                .get(Response.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        String responseBody = response.readEntity(String.class);
        assertTrue(responseBody.contains("Accept=[text/plain]"));
        assertTrue(responseBody.contains("X-Custom-Header=[0000]"));
    }
}
