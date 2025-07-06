package org.gestion.boisson.features.lots.controller;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.gestion.boisson.features.lots.dto.LotDto;
import org.gestion.boisson.features.lots.mappers.LotMapper;
import org.gestion.boisson.features.lots.services.LotService;

@RequestScoped
@Path("/lots")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class LotController {
    @Inject
    private LotService lotService;

    @Inject
    private LotMapper lotMapper;

    @GET
    @Path("/health")
    public Response healthCheck() {
        return Response.ok("Lot service is running").build();
    }
    @GET
    @Path("")
    public Response getAllLots() {
        try {
            log.info("Fetching all lots");
            List<LotDto> lots = lotMapper.toDtoList(lotService.getAllLotsDisponibles());
            return Response.ok(lots).build();
        } catch (Exception e) {
            log.error("Error fetching lots: {}", e.getMessage(), e);
            return Response.serverError().entity("Erreur lors de la récupération des lots").build();
        }
    }
    @POST
    @Path("/")
    public Response addLot(LotDto lotDto) {
        try {
            log.info("Adding new lot: {}", lotDto);
            if (lotDto == null || lotDto.getNumeroLot() == null || lotDto.getBoisson() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Lot data is incomplete").build();
            }
            lotService.save(lotMapper.toEntity(lotDto));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout du lot : {}", e.getMessage(), e);
            return Response.serverError().entity("Erreur lors de l'ajout du lot").build();
        }
    }
    @PUT
    @Path("/")
    public Response updateLot(LotDto lotDto) {
        try {
            log.info("Updating lot: {}", lotDto);
            if (lotDto == null || lotDto.getId() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Lot ID manquant").build();
            }
            boolean success = lotService.updateLot(lotMapper.toEntity(lotDto));
            if (!success) {
                return Response.status(Response.Status.NOT_FOUND).entity("Lot non trouvé").build();
            }
            return Response.ok().build();
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour du lot : {}", e.getMessage(), e);
            return Response.serverError().entity("Erreur lors de la mise à jour du lot").build();
        }
    }
    @GET
    @Path("/{lotId}/is-available")
    public Response isAvailable(@PathParam("lotId") Long lotId) {
        try {
            boolean available = lotService.isLotAvailable(lotId);
            return Response.ok(available).build();
        } catch (Exception e) {
            log.error("Erreur lors de la vérification de disponibilité : {}", e.getMessage(), e);
            return Response.serverError().entity("Erreur lors de la vérification de la disponibilité").build();
        }
    }
    @GET
    @Path("/{lotId}/is-expired")
    public Response isExpired(@PathParam("lotId") Long lotId) {
        try {
            boolean expired = lotService.isExpired(lotId);
            return Response.ok(expired).build();
        } catch (Exception e) {
            log.error("Erreur lors de la vérification de péremption : {}", e.getMessage(), e);
            return Response.serverError().entity("Erreur lors de la vérification de la péremption").build();
        }
    }
    @GET
    @Path("/boisson/{boissonId}/ordre-peremption")
    public Response getLotsByExpirationOrder(@PathParam("boissonId") Long boissonId) {
        // les lots qui expirent en premier sont affichés en premier. FEFO
        // retourne la liste des lots triée par date de péremption de la plus proche à la plus éloignée
        try {
            log.info("Fetching lots by expiration for boisson ID: {}", boissonId);
            List<LotDto> lots = lotMapper.toDtoList(lotService.getAllLotOrderByExpirationDate(boissonId));
            return Response.ok(lots).build();
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des lots triés par date de péremption : {}", e.getMessage(), e);
            return Response.serverError().entity("Erreur lors du tri des lots par date de péremption").build();
        }
    }
    @POST
    @Path("/{lotId}/remove-quantity")
    public Response removeQuantity(@PathParam("lotId") Long lotId, @QueryParam("quantity") int quantity) {
        try {
            log.info("Retrait de {} unités du lot ID {}", quantity, lotId);
            boolean success = lotService.removeQuantityFromLot(lotId, quantity);
            return success ? Response.ok().build() :
                    Response.status(Response.Status.BAD_REQUEST).entity("Stock insuffisant ou lot introuvable").build();
        } catch (Exception e) {
            log.error("Erreur lors du retrait de quantité : {}", e.getMessage(), e);
            return Response.serverError().entity("Erreur lors du retrait de quantité").build();
        }
    }
}
