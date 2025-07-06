package org.gestion.boisson.features.boissons.mappers;

import org.gestion.boisson.features.boissons.dto.BoissonDto;
import org.gestion.boisson.features.boissons.entities.Boisson;

public interface BoissonMapper {
    Boisson toEntity(BoissonDto dto);
    BoissonDto toDto(Boisson entity);
}
