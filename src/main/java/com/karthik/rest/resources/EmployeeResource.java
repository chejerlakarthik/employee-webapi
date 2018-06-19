package com.karthik.rest.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.karthik.rest.business.service.EmployeeService;
import com.karthik.rest.business.service.model.Employee;
import com.karthik.rest.exception.BadRequestException;
import com.karthik.rest.exception.DoesNotExistException;
import com.karthik.rest.params.EmployeeBeanParam;

@Path("/employees")
@Produces(value = { MediaType.APPLICATION_JSON })
@Consumes(value = { MediaType.APPLICATION_JSON })
public class EmployeeResource {

	private EmployeeService service = new EmployeeService();

	@GET
	public Response getAll(@BeanParam EmployeeBeanParam paramsBean, @Context UriInfo uriInfo) {
		
		List<Employee> employees = new ArrayList<Employee>();
		// Filtering parameters
		if (paramsBean.getYear() != null && paramsBean.getYear() > 0) {
			employees = service.readAllFilteredByYear(paramsBean.getYear());
			return Response.ok().entity(employees).build();
		}
		
		// Pagination parameters
		if (paramsBean.getStart() != null && paramsBean.getSize() != null 
				&& paramsBean.getStart() > 0 && paramsBean.getSize() > 0) {
			employees = service.readAllPaginated(paramsBean.getStart(), paramsBean.getSize());
			return Response.ok().entity(employees).build();
		}

		for (Employee employee : service.readAll()) {
			String eid = String.valueOf(employee.getEmpId());
			
			//First reset the links
			employee.resetLinks();
			
			employee.addLink(linkToSelf(uriInfo, eid), "self");
			employee.addLink(linkToAssets(uriInfo, eid), "assets");
			employees.add(employee);
		}
		return Response.ok().entity(employees).build();
	}

	@GET
	@Path("/{empId}")
	public Response get(@PathParam(value = "empId") Long empId, @Context UriInfo uriInfo) throws DoesNotExistException {
		Employee employee = service.read(empId);
		String eid = String.valueOf(empId);
		
		//First reset the links
		employee.resetLinks();
				
		employee.addLink(linkToSelf(uriInfo, eid), "self");
		employee.addLink(linkToAssets(uriInfo, eid), "assets");
		
		return Response.ok().entity(employee).build();
	}

	private String linkToSelf(UriInfo uriInfo, String empId) {
		return uriInfo.getBaseUriBuilder()
					  .path(EmployeeResource.class)
					  .path(empId)
					  .build()
					  .toString();
	}
	
	private String linkToAssets(UriInfo uriInfo, String empId) {
		return uriInfo.getBaseUriBuilder()
					  .path(EmployeeResource.class)
					  .path(EmployeeResource.class, "getAssetResource")
					  .path(AssetResource.class)
					  .resolveTemplate("empId", empId)
					  .build()
					  .toString();
	}

	@POST
	public Response add(Employee employee, @Context UriInfo uriInfo) {
		Employee newEmployee = service.add(employee);
		String newId = String.valueOf(newEmployee.getEmpId());
		URI newResourceLocation = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(newResourceLocation)
					   .entity(newEmployee)
					   .build();
	}

	@PUT
	@Path("/{empId}")
	public Response update(@PathParam(value="empId") Long empId, Employee employee) throws DoesNotExistException, BadRequestException{
		Employee updatedEmployee = service.update(empId, employee);
		return Response.ok().entity(updatedEmployee).build();
	}

	@DELETE
	@Path("/{empId}")
	public Response delete(@PathParam(value="empId") Long empId) {
		service.delete(empId);
		return Response.noContent().build();
	}
	
	@Path("/{empId}/assets")
	public AssetResource getAssetResource() {
		return new AssetResource();
	}

}
