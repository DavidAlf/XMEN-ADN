package com.projects.xmen_adn.aplication.mapper.dto;

import org.springframework.stereotype.Component;

import com.projects.xmen_adn.domain.model.PersonaModel;
import com.projects.xmen_adn.domain.model.dto.PersonaDTO;

@Component
public class PersonaDTOMaper {

    public PersonaDTO modelTOdto(PersonaModel personaModel) {
        return PersonaDTO.builder()
                .personaId(personaModel.getPersonaId())
                .nombre(personaModel.getNombre())
                .apellido(personaModel.getApellido())
                .build();
    }
}
