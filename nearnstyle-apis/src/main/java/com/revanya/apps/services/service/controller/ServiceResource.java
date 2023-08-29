package com.revanya.apps.services.service.controller;


import com.revanya.apps.services.service.dto.ServiceDTO;
import com.revanya.apps.services.service.service.ServiceService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Admin", "Salon Admin", "User"})
public class ServiceResource {

    @Inject
    ServiceService serviceService;

    @GET
    @Path("/{id}")
    public Response getService(@PathParam("id") Long id) {
        ServiceDTO serviceDTO = serviceService.getService(id);
        return Response.ok(serviceDTO).build();
    }

    @GET
    public Response getAllServices() {
        List<ServiceDTO> services = serviceService.getAllServices();
        return Response.ok(services).build();
    }

    @POST
    public Response createService(ServiceDTO serviceDTO) {
        ServiceDTO createdService = serviceService.createService(serviceDTO);
        return Response.ok(createdService).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteService(@PathParam("id") Long id) {
        serviceService.deleteService(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateService(@PathParam("id") Long id, ServiceDTO serviceDTO) {
        ServiceDTO updatedService = serviceService.updateService(id, serviceDTO);
        return Response.ok(updatedService).build();
    }

    @GET
    @Path("/search/{name}")
    public Response searchServicesByName(@PathParam("name") String name) {
        List<ServiceDTO> services = serviceService.searchServicesByName(name);
        return Response.ok(services).build();
    }

    @GET
    @Path("/rating/{threshold}")
    public Response getServicesAboveRating(@PathParam("threshold") Float threshold) {
        List<ServiceDTO> services = serviceService.getServicesAboveRating(threshold);
        return Response.ok(services).build();
    }

    @GET
    @Path("/duration/{duration}")
    public Response getServicesByDuration(@PathParam("duration") Integer duration) {
        List<ServiceDTO> services = serviceService.getServicesByDuration(duration);
        return Response.ok(services).build();
    }





    // ... Add other specific endpoints for Service as required ...
}
