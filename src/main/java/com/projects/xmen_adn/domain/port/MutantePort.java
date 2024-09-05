package com.projects.xmen_adn.domain.port;

import java.util.List;
import java.util.Optional;

import com.projects.xmen_adn.domain.model.MutanteModel;

public interface MutantePort {

    MutanteModel save(MutanteModel mutanteModel);

    MutanteModel update(String id, MutanteModel mutanteModel);

    void delete(String id);

    List<MutanteModel> list();

    Optional<MutanteModel> getByName(String Nombre);

    Optional<MutanteModel> getById(String id);
}