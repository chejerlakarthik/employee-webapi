package com.karthik.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class EmployeeRestApp extends ResourceConfig {

	public EmployeeRestApp() {
		packages("com.karthik.rest;com.karthik.rest.resources;com.karthik.rest.exception.mapper");
	}

}
