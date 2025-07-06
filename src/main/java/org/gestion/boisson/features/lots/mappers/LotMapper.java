package org.gestion.boisson.features.lots.mappers;


import org.gestion.boisson.features.lots.dto.LotDto;
import org.gestion.boisson.features.lots.entities.Lot;

import java.util.List;

/**
 * Mapper interface for converting between Lot and LotDto objects.
 */
public interface LotMapper {

    /**
     * Converts a LotDto to a Lot entity.
     *
     * @param dto the LotDto to convert
     * @return the corresponding Lot entity
     */
    Lot toEntity(LotDto dto);

    /**
     * Converts a Lot entity to a LotDto.
     *
     * @param entity the Lot entity to convert
     * @return the corresponding LotDto
     */
    LotDto toDto(Lot entity);

    /**
     * Converts a list of Lot entities to a list of LotDtos.
     *
     * @param lots the list of Lot entities to convert
     * @return the corresponding list of LotDtos
     */
    List<LotDto> toDtoList(List<Lot> lots);

    /**
     * Converts a list of LotDtos to a list of Lot entities.
     *
     * @param dtos the list of LotDtos to convert
     * @return the corresponding list of Lot entities
     */
    List<Lot> toEntityList(List<LotDto> dtos);
}

