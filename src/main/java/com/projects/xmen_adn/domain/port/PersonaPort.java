package com.projects.xmen_adn.domain.port;

import java.util.List;
import java.util.Optional;

import com.projects.xmen_adn.domain.model.PersonaModel;

public interface PersonaPort {

    PersonaModel save(PersonaModel personaModel);

    PersonaModel update(Long id, PersonaModel personaModel);

    void delete(Long id);

    List<PersonaModel> list();

    Optional<PersonaModel> getByName(String Nombre);

    Optional<PersonaModel> getById(Long id);
}