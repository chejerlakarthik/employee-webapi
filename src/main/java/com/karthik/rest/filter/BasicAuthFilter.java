package com.karthik.rest.filter;

import java.io.IOException;

import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.karthik.rest.exception.ErrorEntity;
import com.karthik.rest.util.StringUtil;

@Provider
public class BasicAuthFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authHeader = requestContext.getHeaderString("Authorization");
		if (StringUtil.isNullOrBlank(authHeader) || isUnauthorized(authHeader)) {
			Response error = Response.status(Status.UNAUTHORIZED)
									 .entity(new ErrorEntity(401, "Access Denied : Unauthorized access"))
									 .build();
			requestContext.abortWith(error);
		}
	}

	private boolean isUnauthorized(String authorizationValue) {
		String minusBasicPrefix = authorizationValue.substring(6);
		String decoded = Base64.decodeAsString(minusBasicPrefix);
		return !decoded.equalsIgnoreCase("username:password");
	}

}
