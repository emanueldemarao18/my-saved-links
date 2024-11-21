package com.github.emanuel.api.resource;


import com.github.emanuel.api.dto.request.LinkRequestDTO;
import com.github.emanuel.api.dto.response.LinkResponseDTO;
import com.github.emanuel.application.service.LinkService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/links")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LinkResource {


   private final LinkService linkService;

    public LinkResource(LinkService linkService) {
        this.linkService = linkService;
    }

    @GET
    public List<LinkResponseDTO> listAll() {
        return linkService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return linkService.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response create(LinkRequestDTO linkRequestDTO) {
        LinkResponseDTO createdLink = linkService.create(linkRequestDTO);
        return Response.status(Response.Status.CREATED).entity(createdLink).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, LinkRequestDTO linkRequestDTO) {
        return linkService.update(id, linkRequestDTO)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = linkService.delete(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}