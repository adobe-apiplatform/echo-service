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

package com.adobe.api.platform.echo.rest;

import com.adobe.api.platform.msc.support.JaxRsComponent;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * @author Cristian Constantin
 * @since 11/20/14
 */
@Path("/echo")
@Produces(MediaType.TEXT_PLAIN)
@JaxRsComponent
public class EchoResource {

    @POST
    @Consumes(MediaType.MEDIA_TYPE_WILDCARD)
    public String echoBody(@NotNull @Size(min = 1) String requestBody) {

        return requestBody;
    }

    @GET
    @Path("/headers")
    @Consumes(MediaType.MEDIA_TYPE_WILDCARD)
    public String echoHeaders(@Context HttpHeaders headers) {

        StringBuilder sb = new StringBuilder();

        headers.getRequestHeaders().forEach((key, value) -> sb.append(key).append("=").append(value).append("\n"));

        return sb.toString();
    }
}
