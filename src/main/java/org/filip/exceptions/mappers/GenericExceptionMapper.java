package org.filip.exceptions.mappers;

import org.filip.exceptions.AppError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable throwable) {
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        AppError appError = new AppError(status.getStatusCode(), "Please contact the administratior");
        return Response.status(status).entity(appError).build();
    }
}
