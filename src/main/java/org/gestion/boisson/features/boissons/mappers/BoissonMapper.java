package org.gestion.boisson.features.boissons.mappers;

import org.gestion.boisson.features.boissons.dto.BoissonDto;
import org.gestion.boisson.features.boissons.entities.Boisson;

import java.util.List;

public interface BoissonMapper {
    Boisson toEntity(BoissonDto dto);
    BoissonDto toDto(Boisson entity);
    List<BoissonDto> toDtoList(List<Boisson> boissons);
    List<Boisson> toEntityList(List<BoissonDto> dtos);
}
