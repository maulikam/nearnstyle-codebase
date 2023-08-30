package com.revanya.apps.services.service.controller;


import com.revanya.apps.services.service.dto.ServiceTypeDTO;
import com.revanya.apps.services.service.service.ServiceTypeService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/service-types")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Admin", "Salon Admin", "User"})
public class ServiceTypeResource {

    @Inject
    ServiceTypeService serviceTypeService;

    @GET
    @Path("/{id}")
    public Response getServiceType(@PathParam("id") Long id) {
        ServiceTypeDTO serviceTypeDTO = serviceTypeService.getServiceType(id);
        return Response.ok(serviceTypeDTO).build();
    }

    @GET
    public Response getAllServiceTypes() {
        List<ServiceTypeDTO> serviceTypes = serviceTypeService.getAllServiceTypes();
        return Response.ok(serviceTypes).build();
    }

    @POST
    public Response createServiceType(ServiceTypeDTO serviceTypeDTO) {
        ServiceTypeDTO createdServiceType = serviceTypeService.createServiceType(serviceTypeDTO);
        return Response.ok(createdServiceType).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteServiceType(@PathParam("id") Long id) {
        serviceTypeService.deleteServiceType(id);
        return Response.noContent().build();
    }

    // ... Add other specific endpoints for ServiceType as required ...
}

