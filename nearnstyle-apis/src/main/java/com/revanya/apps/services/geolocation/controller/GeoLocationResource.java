package com.revanya.apps.services.geolocation.controller;

import com.revanya.apps.services.geolocation.dto.GeoLocationDTO;
import com.revanya.apps.services.geolocation.service.GeoLocationService;
import jakarta.inject.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/geolocations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeoLocationResource {

    @Inject
    GeoLocationService geoLocationService;

    @POST
    public Response createGeoLocation(GeoLocationDTO geoLocationDTO) {
        GeoLocationDTO createdGeoLocation = geoLocationService.createGeoLocation(geoLocationDTO);
        return Response.ok(createdGeoLocation).build();
    }

    @GET
    public Response getAllGeoLocations() {
        List<GeoLocationDTO> geoLocations = geoLocationService.getAllGeoLocations();
        return Response.ok(geoLocations).build();
    }

    // Add more specific endpoints as needed

}

