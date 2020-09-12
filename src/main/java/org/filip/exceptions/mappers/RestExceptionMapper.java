package org.filip.exceptions.mappers;

import org.filip.exceptions.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;

@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException> {
    @Override
    public Response toResponse(RestException e) {
        AppError appError = new AppError(e.getHttpStatus().getStatusCode(), e.getMessage());
        return Response.status(e.getHttpStatus()).entity(appError).build();
    }
}
