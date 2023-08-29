package com.revanya.apps.services.salon.controller;


import com.revanya.apps.services.salon.dto.SalonDTO;
import com.revanya.apps.services.salon.service.SalonService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/salons")
@RolesAllowed({"Admin", "Salon Admin", "User"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SalonResource {

    @Inject
    SalonService salonService;

    @GET
    @Path("/{id}")
    public Response getSalonById(@PathParam("id") Long id) {
        SalonDTO salon = salonService.getSalonById(id);
        if (salon != null) {
            return Response.ok(salon).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/name/{name}")
    public List<SalonDTO> getSalonsByName(@PathParam("name") String name) {
        return salonService.getSalonsByName(name);
    }

    // Similarly, you can expose endpoints for other query methods...
    // E.g., getSalonsByCity, getSalonsByState, and so forth...

    @POST
    @PermitAll
    public Response addSalon(SalonDTO salonDTO) {
        salonService.addSalon(salonDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateSalon(@PathParam("id") Long id, SalonDTO salonDTO) {
        if (salonService.getSalonById(id) != null) {
            salonDTO.setId(id);  // Ensure the ID in the path is set in the DTO
            salonService.updateSalon(salonDTO);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSalon(@PathParam("id") Long id) {
        salonService.deleteSalon(id);
        return Response.noContent().build();
    }
}

