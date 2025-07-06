package org.gestion.boisson.features.boissons.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.gestion.boisson.features.boissons.dto.BoissonDto;
import org.gestion.boisson.features.boissons.mappers.BoissonMapper;
import org.gestion.boisson.features.boissons.services.BoissonService;

@RequestScoped
@Path("/boissons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class BoissonController {
    @Inject
    private BoissonService boissonService;
    @Inject
    private BoissonMapper boissonMapper;
    @GET
    @Path("/health")
    public Response healthCheck() {
        return Response.ok("Service is running").build();
    }
    @GET
    @Path("")
    public Response getBoissons() {
        try {
            log.info("Fetching all boissons");
            return Response.ok(boissonMapper.toDtoList(boissonService.getAllBoissons())).build();

        } catch (Exception e) {
            log.error("Error fetching boissons: {}", e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error fetching boissons: " + e.getMessage()).build();
        }
    }
    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/*+json"})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addBoissons(BoissonDto boissonDto) {
        try {
            log.info("Adding new boisson: {}", boissonDto);
            if (boissonDto == null || boissonDto.getNom() == null || boissonDto.getPrixUnitaire() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Boisson data is incomplete or invalid").build();
            }
            var boisson = boissonMapper.toEntity(boissonDto);
            var savedBoisson = boissonService.ajouterBoisson(boisson);
            return Response.status(Response.Status.CREATED).entity(boissonMapper.toDto(savedBoisson)).build();
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout de la boisson : {}", e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de l'ajout de la boisson : " + e.getMessage()).build();
        }
    }
}
