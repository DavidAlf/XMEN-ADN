package com.projects.xmen_adn.aplication.useCases;

import java.util.List;

import com.projects.xmen_adn.domain.model.dto.MutanteDTO;
import com.projects.xmen_adn.domain.model.request.MutanteRequest;

public interface MutanteUseCase {

    MutanteDTO save(MutanteRequest mutanteRequest);

    MutanteDTO update(String id, MutanteRequest mutanteRequest);

    void delete(String id);

    List<MutanteDTO> list();

    MutanteDTO getByName(String name);
}
