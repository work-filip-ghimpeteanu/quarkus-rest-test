package org.filip.exceptions;

import javax.ws.rs.core.Response;

public class RestException extends RuntimeException {

    Response.Status httpStatus;

    public RestException(Response.Status httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public Response.Status getHttpStatus() {
        return httpStatus;
    }
}
