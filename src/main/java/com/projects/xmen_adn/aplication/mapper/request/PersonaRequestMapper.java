package com.projects.xmen_adn.aplication.mapper.request;

import org.springframework.stereotype.Component;

import com.projects.xmen_adn.domain.model.PersonaModel;
import com.projects.xmen_adn.domain.model.request.PersonaRequest;

@Component
public class PersonaRequestMapper {

    public PersonaModel requestTOmodel(PersonaRequest personaRequest) {
        return PersonaModel.builder()
                .nombre(personaRequest.getNombre())
                .apellido(personaRequest.getApellido())
                .build();
    }
}
