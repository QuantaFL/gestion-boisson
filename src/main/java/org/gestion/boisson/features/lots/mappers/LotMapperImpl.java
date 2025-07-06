package org.gestion.boisson.features.lots.mappers;

import jakarta.inject.Inject;
import org.gestion.boisson.features.boissons.mappers.BoissonMapper;
import org.gestion.boisson.features.lots.dto.LotDto;
import org.gestion.boisson.features.lots.entities.Lot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the LotMapper interface.
 */
public class LotMapperImpl implements LotMapper {
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Inject
    private BoissonMapper boissonMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Lot toEntity(LotDto dto) {
        if (dto == null) return null;

        return Lot.builder()
                .id(dto.getId())
                .numeroLot(dto.getNumeroLot())
                .quantiteInitial(dto.getQuantiteInitial())
                .quantiteActuelle(dto.getQuantiteActuelle())
                .vendable(Boolean.TRUE.equals(dto.getVendable()))
                .dateEntree(dto.getDateEntree() != null ? LocalDateTime.parse(dto.getDateEntree(), formatter) : null)
                .datePeremption(dto.getDatePeremption() != null ? LocalDateTime.parse(dto.getDatePeremption(), formatter) : null)
                .boisson(boissonMapper.toEntity(dto.getBoisson()))
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LotDto toDto(Lot entity) {
        if (entity == null) return null;

        LotDto dto = new LotDto();
        dto.setId(entity.getId());
        dto.setNumeroLot(entity.getNumeroLot());
        dto.setQuantiteInitial(entity.getQuantiteInitial());
        dto.setQuantiteActuelle(entity.getQuantiteActuelle());
        dto.setDateEntree(entity.getDateEntree() != null ? entity.getDateEntree().format(formatter) : null);
        dto.setDatePeremption(entity.getDatePeremption() != null ? entity.getDatePeremption().format(formatter) : null);
        dto.setVendable(entity.isVendable());
        dto.setBoisson(boissonMapper.toDto(entity.getBoisson()));
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LotDto> toDtoList(List<Lot> lots) {
        return lots.stream().map(this::toDto).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Lot> toEntityList(List<LotDto> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());

    }
}