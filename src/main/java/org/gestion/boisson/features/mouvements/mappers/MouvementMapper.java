package org.gestion.boisson.features.mouvements.mappers;

import org.gestion.boisson.features.mouvements.dto.MouvementDto;
import org.gestion.boisson.features.mouvements.entities.Mouvement;
import java.util.List;

public interface MouvementMapper {
    MouvementDto toDto(Mouvement mouvement);
    Mouvement toEntity(MouvementDto dto);
    List<MouvementDto> toDtoList(List<Mouvement> mouvements);
    List<Mouvement> toEntityList(List<MouvementDto> dtos);
}

