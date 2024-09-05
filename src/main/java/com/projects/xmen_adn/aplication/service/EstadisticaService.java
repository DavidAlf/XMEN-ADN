package com.projects.xmen_adn.aplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.xmen_adn.aplication.mapper.dto.EstadisticaDTOMapper;
import com.projects.xmen_adn.aplication.useCases.EstadisticasUseCase;
import com.projects.xmen_adn.domain.model.dto.EstadisticaDTO;
import com.projects.xmen_adn.domain.port.EstadisticaPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EstadisticaService implements EstadisticasUseCase {

    @Autowired
    private EstadisticaPort estadisticaPort;

    @Autowired
    private EstadisticaDTOMapper estadisticaDTOMapper;

    @Override
    public EstadisticaDTO getEstadistica() {
        log.info("[EstadisticaService(getEstadistica)] -> Estadisticas");

        var estadisticaModel = estadisticaPort.getEstadistica();

        return estadisticaDTOMapper.modelTOdto(estadisticaModel.get());
    }

}
