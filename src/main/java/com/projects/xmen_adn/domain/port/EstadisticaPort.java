package com.projects.xmen_adn.domain.port;

import java.util.Optional;

import com.projects.xmen_adn.domain.model.EstadisticaModel;

public interface EstadisticaPort {

    Optional<EstadisticaModel> getEstadistica();
}
