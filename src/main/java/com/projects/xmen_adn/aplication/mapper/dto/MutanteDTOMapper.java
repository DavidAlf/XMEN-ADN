package com.projects.xmen_adn.aplication.mapper.dto;

import com.projects.xmen_adn.domain.model.MutanteModel;
import com.projects.xmen_adn.domain.model.dto.MutanteDTO;
import com.projects.xmen_adn.infrastructure.config.util.Mapper;

@Mapper
public class MutanteDTOMapper {

    public MutanteDTO modelTOdto(MutanteModel mutanteModel) {
        return MutanteDTO.builder()
                .mutanteId(mutanteModel.getMutanteId())
                .nombre(mutanteModel.getNombre())
                .apellido(mutanteModel.getApellido())
                .adn(mutanteModel.getAdn())
                .isMutante(mutanteModel.isMutante())
                .build();
    }
}
