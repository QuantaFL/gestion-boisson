package org.gestion.boisson.features.boissons.mappers;

import org.gestion.boisson.features.boissons.dto.BoissonDto;
import org.gestion.boisson.features.boissons.entities.Boisson;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class BoissonMapperImpl implements BoissonMapper {


    @Override
    public Boisson toEntity(BoissonDto dto) {
        if (dto == null) return null;

        Boisson entity = new Boisson();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setDescription(dto.getDescription());
        entity.setVolume(dto.getVolume());
        entity.setUnite(dto.getUnite());
        entity.setPrixUnitaire(dto.getPrixUnitaire());
        entity.setSeuil(dto.getSeuil() != null ? dto.getSeuil() : 0);
        entity.setActive(dto.getIsActive() != null ? dto.getIsActive() : true);

        if (dto.getCreatedAt() != null) {
            try {
                entity.setCreatedAt(LocalDateTime.parse(dto.getCreatedAt()));
            } catch (DateTimeParseException ignored) {}
        }

        if (dto.getUpdatedAt() != null) {
            try {
                entity.setUpdatedAt(LocalDateTime.parse(dto.getUpdatedAt()));
            } catch (DateTimeParseException ignored) {}
        }

        return entity;

    }

    @Override
    public BoissonDto toDto(Boisson entity) {
        if (entity == null) return null;

        BoissonDto dto = new BoissonDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setDescription(entity.getDescription());
        dto.setVolume(entity.getVolume());
        dto.setUnite(entity.getUnite());
        dto.setPrixUnitaire(entity.getPrixUnitaire());
        dto.setSeuil(entity.getSeuil());
        dto.setIsActive(entity.isActive());

        if (entity.getCreatedAt() != null)
            dto.setCreatedAt(entity.getCreatedAt().toString());

        if (entity.getUpdatedAt() != null)
            dto.setUpdatedAt(entity.getUpdatedAt().toString());

        return dto;
    }
}
