package com.projects.xmen_adn.infrastructure.adapter.mapper;

import com.projects.xmen_adn.domain.model.MutanteModel;
import com.projects.xmen_adn.infrastructure.adapter.entityes.MutanteEntity;
import com.projects.xmen_adn.infrastructure.config.util.Mapper;

@Mapper
public class MutanteMapper {

    public MutanteEntity modelTOentity(MutanteModel mutanteModel) {
        return MutanteEntity.builder()
                .mutanteId(mutanteModel.getMutanteId())
                .nombre(mutanteModel.getNombre())
                .apellido(mutanteModel.getApellido())
                .adn(mutanteModel.getAdn())
                .isMutante(mutanteModel.isMutante())
                .build();
    }

    public MutanteModel entityTOmodel(MutanteEntity mutanteEntity) {
        return MutanteModel.builder()
                .mutanteId(mutanteEntity.getMutanteId())
                .nombre(mutanteEntity.getNombre())
                .apellido(mutanteEntity.getApellido())
                .adn(mutanteEntity.getAdn())
                .isMutante(mutanteEntity.isMutante())
                .build();
    }

}
