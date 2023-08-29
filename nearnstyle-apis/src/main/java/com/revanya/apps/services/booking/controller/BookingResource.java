package com.revanya.apps.services.booking.controller;

import com.revanya.apps.services.booking.dto.BookingDTO;
import com.revanya.apps.services.booking.service.BookingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

    @Inject
    BookingService bookingService;

    @GET
    @Path("/{id}")
    public Response getBooking(@PathParam("id") Long id) {
        BookingDTO bookingDTO = bookingService.getBooking(id);
        return Response.ok(bookingDTO).build();
    }

    @GET
    public Response getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return Response.ok(bookings).build();
    }

    @POST
    public Response createBooking(BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return Response.ok(createdBooking).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBooking(@PathParam("id") Long id, BookingDTO bookingDTO) {
        BookingDTO updatedBooking = bookingService.updateBooking(id, bookingDTO);
        return Response.ok(updatedBooking).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBooking(@PathParam("id") Long id) {
        bookingService.deleteBooking(id);
        return Response.noContent().build();
    }

    // ... Add other specific endpoints for Booking as required ...
}

