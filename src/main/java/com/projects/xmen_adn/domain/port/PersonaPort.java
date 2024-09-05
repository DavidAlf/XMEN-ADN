package com.projects.xmen_adn.domain.port;

import java.util.List;
import java.util.Optional;

import com.projects.xmen_adn.domain.model.PersonaModel;

public interface PersonaPort {

    PersonaModel save(PersonaModel personaModel);

    PersonaModel update(String id, PersonaModel personaModel);

    void delete(String id);

    List<PersonaModel> list();

    Optional<PersonaModel> getByName(String Nombre);

    Optional<PersonaModel> getById(String id);
}