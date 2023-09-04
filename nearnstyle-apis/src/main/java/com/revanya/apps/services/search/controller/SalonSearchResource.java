package com.revanya.apps.services.search.controller;

import com.revanya.apps.services.search.dto.SalonDetailsDTO;
import com.revanya.apps.services.search.service.SalonSearchService;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("/salon-search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SalonSearchResource {

    @Inject
    SalonSearchService salonSearchService;

    @GET
    @Path("/by-name")
    public List<SalonDetailsDTO> searchSalonByName(@QueryParam("name") String name,
                                                   @QueryParam("pageIndex") int pageIndex,
                                                   @QueryParam("pageSize") int pageSize,
                                                   @QueryParam("sort") String sortField) {
        return salonSearchService.searchSalonByName(name, Page.of(pageIndex, pageSize), Sort.by(sortField));
    }

    @GET
    @Path("/by-service-types")
    public List<SalonDetailsDTO> searchSalonByServiceTypes(List<Long> serviceTypeIds,
                                                           @QueryParam("pageIndex") int pageIndex,
                                                           @QueryParam("pageSize") int pageSize,
                                                           @QueryParam("sort") String sortField) {
        return salonSearchService.searchSalonByServiceTypes(serviceTypeIds, Page.of(pageIndex, pageSize), Sort.by(sortField));
    }

    @GET
    @Path("/by-operating-hours")
    public List<SalonDetailsDTO> searchSalonByOperatingHours(@QueryParam("from") Date from,
                                                             @QueryParam("to") Date to,
                                                             @QueryParam("pageIndex") int pageIndex,
                                                             @QueryParam("pageSize") int pageSize,
                                                             @QueryParam("sort") String sortField) {
        return salonSearchService.searchSalonByOperatingHours(from, to, Page.of(pageIndex, pageSize), Sort.by(sortField));
    }

    // ... Continue for other methods

    @GET
    @Path("/{salonId}")
    public SalonDetailsDTO getSalonDetailsById(@PathParam("salonId") Long salonId) {
        return salonSearchService.getSalonDetailsById(salonId);
    }
}

