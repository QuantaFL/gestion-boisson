package org.gestion.boisson.features.mouvements.mappers;

import org.gestion.boisson.features.mouvements.dto.MouvementDto;
import org.gestion.boisson.features.mouvements.entities.Mouvement;
import org.gestion.boisson.features.boissons.entities.Boisson;
import org.gestion.boisson.features.lots.entities.Lot;
import org.gestion.boisson.features.utilisateurs.entities.Utilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class MouvementMapperImpl implements MouvementMapper {
    @Override
    public MouvementDto toDto(Mouvement mouvement) {
        if (mouvement == null) return null;
        MouvementDto dto = new MouvementDto();
        dto.setId(mouvement.getId());
        dto.setType(mouvement.getType());
        dto.setDateMouvement(mouvement.getDateMouvement());
        dto.setQuantite(mouvement.getQuantite());
        if (mouvement.getBoisson() != null) {
            dto.setBoissonId(mouvement.getBoisson().getId());
            dto.setBoissonNom(mouvement.getBoisson().getNom());
        }
        if (mouvement.getLot() != null) {
            dto.setLotId(mouvement.getLot().getId());
            dto.setLotNumero(mouvement.getLot().getNumeroLot());
        }
        if (mouvement.getUtilisateur() != null) {
            dto.setUtilisateurId(mouvement.getUtilisateur().getId());
            dto.setUtilisateurEmail(mouvement.getUtilisateur().getEmail());
        }
        dto.setTypeAjustement(mouvement.getTypeAjustement());
        return dto;
    }

    @Override
    public Mouvement toEntity(MouvementDto dto) {
        if (dto == null) return null;
        Mouvement.MouvementBuilder builder = Mouvement.builder();
        builder.id(dto.getId());
        builder.type(dto.getType());
        builder.dateMouvement(dto.getDateMouvement());
        builder.quantite(dto.getQuantite());
        if (dto.getBoissonId() != null || dto.getBoissonNom() != null) {
            Boisson boisson = new Boisson();
            boisson.setId(dto.getBoissonId());
            boisson.setNom(dto.getBoissonNom());
            builder.boisson(boisson);
        }
        if (dto.getLotId() != null || dto.getLotNumero() != null) {
            Lot lot = new Lot();
            lot.setId(dto.getLotId());
            lot.setNumeroLot(dto.getLotNumero());
            builder.lot(lot);
        }
        if (dto.getUtilisateurId() != null || dto.getUtilisateurEmail() != null) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(dto.getUtilisateurId());
            utilisateur.setEmail(dto.getUtilisateurEmail());
            builder.utilisateur(utilisateur);
        }
        builder.typeAjustement(dto.getTypeAjustement());
        return builder.build();
    }

    @Override
    public List<MouvementDto> toDtoList(List<Mouvement> mouvements) {
        if (mouvements == null) return null;
        List<MouvementDto> list = new ArrayList<>();
        for (Mouvement m : mouvements) {
            list.add(toDto(m));
        }
        return list;
    }

    @Override
    public List<Mouvement> toEntityList(List<MouvementDto> dtos) {
        if (dtos == null) return null;
        List<Mouvement> list = new ArrayList<>();
        for (MouvementDto dto : dtos) {
            list.add(toEntity(dto));
        }
        return list;
    }
}
