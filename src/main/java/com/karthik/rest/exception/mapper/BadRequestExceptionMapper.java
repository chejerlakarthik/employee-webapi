package com.karthik.rest.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.karthik.rest.exception.BadRequestException;
import com.karthik.rest.exception.ErrorEntity;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException exception) {
		return Response.status(Status.BAD_REQUEST)
					   .entity(new ErrorEntity(400, exception.getMessage()))
					   .build();
	}

}
