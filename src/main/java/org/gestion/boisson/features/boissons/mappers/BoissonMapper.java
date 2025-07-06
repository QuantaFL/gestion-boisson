package org.gestion.boisson.features.boissons.mappers;

import org.gestion.boisson.features.boissons.dto.BoissonDto;
import org.gestion.boisson.features.boissons.entities.Boisson;

import java.util.List;

/**
 * Mapper interface for converting between Boisson and BoissonDto objects.
 */
public interface BoissonMapper {
    /**
     * Converts a BoissonDto to a Boisson entity.
     *
     * @param dto the BoissonDto to convert
     * @return the corresponding Boisson entity
     */
    Boisson toEntity(BoissonDto dto);

    /**
     * Converts a Boisson entity to a BoissonDto.
     *
     * @param entity the Boisson entity to convert
     * @return the corresponding BoissonDto
     */
    BoissonDto toDto(Boisson entity);

    /**
     * Converts a list of Boisson entities to a list of BoissonDtos.
     *
     * @param boissons the list of Boisson entities to convert
     * @return the corresponding list of BoissonDtos
     */
    List<BoissonDto> toDtoList(List<Boisson> boissons);

    /**
     * Converts a list of BoissonDtos to a list of Boisson entities.
     *
     * @param dtos the list of BoissonDtos to convert
     * @return the corresponding list of Boisson entities
     */
    List<Boisson> toEntityList(List<BoissonDto> dtos);
}
