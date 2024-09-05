package com.projects.xmen_adn.aplication.useCases;

import java.util.List;

import com.projects.xmen_adn.domain.model.dto.PersonaDTO;
import com.projects.xmen_adn.domain.model.request.PersonaRequest;

public interface PersonaUseCase {

    PersonaDTO save(PersonaRequest personaRequest);

    PersonaDTO update(Long id, PersonaRequest personaRequest);

    void delete(Long id);

    List<PersonaDTO> list();

    PersonaDTO getByName(String name);
}
