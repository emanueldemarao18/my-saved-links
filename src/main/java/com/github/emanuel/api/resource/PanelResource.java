package com.github.emanuel.api.resource;

import com.github.emanuel.api.dto.request.PanelRequestDTO;
import com.github.emanuel.api.dto.request.PanelUpdateRequestDTO;
import com.github.emanuel.api.dto.response.PanelResponseDTO;
import com.github.emanuel.application.service.PanelService;
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

@Path("/panels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PanelResource {


    private final PanelService panelService;

    public PanelResource(PanelService panelService) {
        this.panelService = panelService;
    }

    @GET
    public List<PanelResponseDTO> listAll() {
        return panelService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return panelService.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response create(PanelRequestDTO panelRequestDTO) {
        PanelResponseDTO createdPanel = panelService.create(panelRequestDTO);
        return Response.status(Response.Status.CREATED).entity(createdPanel).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PanelUpdateRequestDTO panelUpdateRequestDTO) {
        return panelService.update(id, panelUpdateRequestDTO)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = panelService.delete(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    public Response deleteAll() {
        Long deletedCount = panelService.deleteAll();
        if (deletedCount > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}