package org.gestion.boisson.features.boissons.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gestion.boisson.features.boissons.mappers.BoissonMapper;
import org.gestion.boisson.features.boissons.services.BoissonService;

@RequestScoped
@Path("/boissons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
}
