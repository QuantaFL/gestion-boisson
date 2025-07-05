package org.gestion.boisson.features.mouvements.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gestion.boisson.features.mouvements.dto.MouvementDto;
import org.gestion.boisson.features.mouvements.entities.Mouvement;
import org.gestion.boisson.features.mouvements.enums.TypeAjustement;
import org.gestion.boisson.features.mouvements.enums.TypeMouvement;
import org.gestion.boisson.features.mouvements.mappers.MouvementMapper;
import org.gestion.boisson.features.mouvements.services.MouvementService;

import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Path("/mouvements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MouvementController {
    @Inject
    private MouvementService mouvementService;
    @Inject
    private MouvementMapper mouvementMapper;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/*+json"})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response enregistrerMouvement(MouvementDto dto) {
        Mouvement mouvement = mouvementMapper.toEntity(dto);
        Mouvement saved = mouvementService.enregistrerMouvement(mouvement);
        return Response.status(Response.Status.CREATED).entity(mouvementMapper.toDto(saved)).build();
    }

    @POST
    @Path("/batch")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/*+json"})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response enregistrerMouvementsBatch(List<MouvementDto> dtos) {
        List<Mouvement> mouvements = dtos.stream().map(mouvementMapper::toEntity).collect(Collectors.toList());
        List<Mouvement> saved = mouvementService.enregistrerMouvementsBatch(mouvements);
        List<MouvementDto> result = saved.stream().map(mouvementMapper::toDto).collect(Collectors.toList());
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @POST
    @Path("/retour")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/*+json"})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response enregistrerRetour(MouvementDto dto, @QueryParam("raison") String raison) {
        Mouvement mouvement = mouvementMapper.toEntity(dto);
        Mouvement saved = mouvementService.enregistrerRetour(mouvement, raison);
        return Response.status(Response.Status.CREATED).entity(mouvementMapper.toDto(saved)).build();
    }

    @POST
    @Path("/ajustement")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/*+json"})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response enregistrerAjustement(MouvementDto dto, @QueryParam("raison") String raison) {
        Mouvement mouvement = mouvementMapper.toEntity(dto);
        Mouvement saved = mouvementService.enregistrerAjustement(mouvement, raison);
        return Response.status(Response.Status.CREATED).entity(mouvementMapper.toDto(saved)).build();
    }

    @GET
    @Path("/boisson/{boissonId}")
    public Response getMouvementsByBoisson(@PathParam("boissonId") Long boissonId) {
        List<Mouvement> mouvements = mouvementService.getAllMouvementsByBoisson(boissonId);
        List<MouvementDto> dtos = mouvements.stream().map(mouvementMapper::toDto).collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/lot/{lotId}")
    public Response getMouvementsByLot(@PathParam("lotId") Long lotId) {
        List<Mouvement> mouvements = mouvementService.getAllMouvementsByLot(lotId);
        List<MouvementDto> dtos = mouvements.stream().map(mouvementMapper::toDto).collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/utilisateur/{utilisateurId}")
    public Response getMouvementsByUtilisateur(@PathParam("utilisateurId") Long utilisateurId) {
        List<Mouvement> mouvements = mouvementService.getAllMouvementsByUtilisateur(utilisateurId);
        List<MouvementDto> dtos = mouvements.stream().map(mouvementMapper::toDto).collect(Collectors.toList());
        return Response.ok(dtos).build();
    }
}
