package com.revanya.apps.services.salon.controller;


import com.revanya.apps.services.salon.dto.SalonDTO;
import com.revanya.apps.services.salon.service.SalonService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/salons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SalonResource {

    @Inject
    SalonService salonService;

    @GET
    @Path("/{id}")
    public Response getSalonById(@PathParam("id") Long id) {
        SalonDTO salonDTO = salonService.getSalonById(id);
        if (salonDTO != null) {
            return Response.ok(salonDTO).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/name/{name}")
    public List<SalonDTO> getSalonsByName(@PathParam("name") String name) {
        return salonService.getSalonsByName(name);
    }

    @POST
    public Response addSalon(SalonDTO salonDTO) {
        salonService.addSalon(salonDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateSalon(SalonDTO salonDTO) {
        salonService.updateSalon(salonDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSalon(@PathParam("id") Long id) {
        salonService.deleteSalon(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/city/{city}")
    public List<SalonDTO> getSalonsByCity(@PathParam("city") String city) {
        return salonService.getSalonsByCity(city);
    }

    @GET
    @Path("/state/{state}")
    public List<SalonDTO> getSalonsByState(@PathParam("state") String state) {
        return salonService.getSalonsByState(state);
    }

    @GET
    @Path("/country/{country}")
    public List<SalonDTO> getSalonsByCountry(@PathParam("country") String country) {
        return salonService.getSalonsByCountry(country);
    }

    @GET
    @Path("/postalCode/{postalCode}")
    public List<SalonDTO> getSalonsByPostalCode(@PathParam("postalCode") String postalCode) {
        return salonService.getSalonsByPostalCode(postalCode);
    }

    @GET
    @Path("/rating/{rating}")
    public List<SalonDTO> getSalonsByAverageRatingAbove(@PathParam("rating") Double rating) {
        return salonService.getSalonsByAverageRatingAbove(rating);
    }

    @GET
    @Path("/email/{email}")
    public List<SalonDTO> getSalonsByEmail(@PathParam("email") String email) {
        return salonService.getSalonsByEmail(email);
    }

    @GET
    @Path("/phoneNumber/{phoneNumber}")
    public List<SalonDTO> getSalonsByPhoneNumber(@PathParam("phoneNumber") String phoneNumber) {
        return salonService.getSalonsByPhoneNumber(phoneNumber);
    }

    @GET
    @Path("/serviceTypeName/{serviceTypeName}")
    public List<SalonDTO> getSalonsByServiceTypeName(@PathParam("serviceTypeName") String serviceTypeName) {
        return salonService.getSalonsByServiceTypeName(serviceTypeName);
    }

    @GET
    @Path("/locationId/{locationId}")
    public List<SalonDTO> getSalonsByGeoLocationId(@PathParam("locationId") Long locationId) {
        return salonService.getSalonsByGeoLocationId(locationId);
    }

}
