package com.projects.xmen_adn.aplication.mapper.request;

import com.projects.xmen_adn.domain.model.MutanteModel;
import com.projects.xmen_adn.domain.model.request.MutanteRequest;
import com.projects.xmen_adn.infrastructure.config.util.Mapper;

@Mapper
public class MutanteRequestMapper {

    public MutanteModel requestTOmodel(MutanteRequest mutanteRequest) {
        return MutanteModel.builder()
                .nombre(mutanteRequest.getNombre())
                .apellido(mutanteRequest.getApellido())
                .adn(mutanteRequest.getAdn())
                .build();
    }
}
