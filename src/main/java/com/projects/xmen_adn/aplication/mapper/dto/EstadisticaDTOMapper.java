package com.projects.xmen_adn.aplication.mapper.dto;

import com.projects.xmen_adn.domain.model.EstadisticaModel;
import com.projects.xmen_adn.domain.model.dto.EstadisticaDTO;
import com.projects.xmen_adn.infrastructure.config.util.Mapper;

@Mapper
public class EstadisticaDTOMapper {

    public EstadisticaDTO modelTOdto(EstadisticaModel estadisticaModel) {
        return EstadisticaDTO.builder()
                .count_human_dna(estadisticaModel.getCount_human_dna())
                .count_mutant_dna(estadisticaModel.getCount_mutant_dna())
                .ratio(estadisticaModel.getRatio())
                .build();
    }
}
