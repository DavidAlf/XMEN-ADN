package com.projects.xmen_adn.infrastructure.adapter.mapper;

import org.springframework.stereotype.Component;

import com.projects.xmen_adn.domain.model.PersonaModel;
import com.projects.xmen_adn.infrastructure.adapter.entityes.PersonaEntity;

@Component
public class PersonaMapper {

    public PersonaEntity modelTOentity(PersonaModel personaModel) {
        return PersonaEntity.builder()
                .personaId(personaModel.getPersonaId())
                .nombre(personaModel.getNombre())
                .apellido(personaModel.getApellido())
                .build();
    }

    public PersonaModel entityTOmodel(PersonaEntity personaEntity) {
        return PersonaModel.builder()
                .personaId(personaEntity.getPersonaId())
                .nombre(personaEntity.getNombre())
                .apellido(personaEntity.getApellido())
                .build();
    }

}
