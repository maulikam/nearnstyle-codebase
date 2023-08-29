package com.revanya.apps.services.user.controller;


import com.revanya.apps.services.user.dto.UserDTO;
import com.revanya.apps.services.user.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Date;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Path("/username/{username}")
    public Response getUserByUsername(@PathParam("username") String username) {
        UserDTO user = userService.findByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @GET
    @Path("/email/{email}")
    public Response getUserByEmail(@PathParam("email") String email) {
        UserDTO user = userService.findByEmail(email);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @GET
    @Path("/city/{city}")
    public List<UserDTO> getUsersByCity(@PathParam("city") String city) {
        return userService.findByCity(city);
    }

    @GET
    @Path("/registered-after/{date}")
    public List<UserDTO> getUsersRegisteredAfter(@PathParam("date") Date date) {
        return userService.findUsersRegisteredAfter(date);
    }

    @GET
    @Path("/role/{roleName}")
    public List<UserDTO> getUsersByRoleName(@PathParam("roleName") String roleName) {
        return userService.findByRoleName(roleName);
    }

    @POST
    public Response createUser(UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return Response.ok(createdUser).build();
    }

    @PUT
    @Path("/{userId}")
    public Response updateUser(@PathParam("userId") Long userId, UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return Response.ok(updatedUser).build();
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId") Long userId) {
        userService.deleteUser(userId);
        return Response.noContent().build();
    }
}

