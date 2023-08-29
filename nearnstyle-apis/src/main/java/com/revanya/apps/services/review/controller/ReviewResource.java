package com.revanya.apps.services.review.controller;


import com.revanya.apps.services.review.dto.ReviewDTO;
import com.revanya.apps.services.review.service.ReviewService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Admin", "Salon Admin", "User"})
public class ReviewResource {

    @Inject
    ReviewService reviewService;

    @GET
    @Path("/{id}")
    public Response getReview(@PathParam("id") Long id) {
        ReviewDTO reviewDTO = reviewService.getReview(id);
        return Response.ok(reviewDTO).build();
    }

    @GET
    public Response getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return Response.ok(reviews).build();
    }

    @POST
    public Response createReview(ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.createReview(reviewDTO);
        return Response.ok(createdReview).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateReview(@PathParam("id") Long id, ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.updateReview(id, reviewDTO);
        return Response.ok(updatedReview).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReview(@PathParam("id") Long id) {
        reviewService.deleteReview(id);
        return Response.noContent().build();
    }

    // ... Add other specific endpoints for Review as required ...
}

