package com.projects.xmen_adn.aplication.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.xmen_adn.aplication.mapper.dto.MutanteDTOMapper;
import com.projects.xmen_adn.aplication.mapper.request.MutanteRequestMapper;
import com.projects.xmen_adn.aplication.useCases.MutanteUseCase;
import com.projects.xmen_adn.domain.model.dto.MutanteDTO;
import com.projects.xmen_adn.domain.model.request.MutanteRequest;
import com.projects.xmen_adn.domain.port.MutantePort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MutanteService implements MutanteUseCase {

    @Autowired
    private MutantePort mutantePort;

    @Autowired
    private MutanteRequestMapper mutanteRequestMapper;

    @Autowired
    private MutanteDTOMapper mutanteDTOMaper;

    @Override
    public MutanteDTO save(MutanteRequest mutanteRequest) {
        log.info("[MutanteService(save)] -> Guardando");

        var mutanteModel = mutanteRequestMapper.requestTOmodel(mutanteRequest);
        var mutanteModelSave = mutantePort.save(mutanteModel);

        return mutanteDTOMaper.modelTOdto(mutanteModelSave);
    }

    @Override
    public MutanteDTO update(String id, MutanteRequest mutanteRequest) {
        log.info("[MutanteService(update)] -> Actualizando");

        var mutanteModel = mutanteRequestMapper.requestTOmodel(mutanteRequest);

        var mutanteModelUpdate = mutantePort.update(id, mutanteModel);

        return mutanteDTOMaper.modelTOdto(mutanteModelUpdate);
    }

    @Override
    public void delete(String id) {
        log.info("[MutanteService(delete)] -> Eliminando");

        mutantePort.delete(id);
    }

    @Override
    public List<MutanteDTO> list() {
        log.info("[MutanteService(list)] -> Listando");

        var mutanteModel = mutantePort.list();

        return mutanteModel.stream().map(mutanteDTOMaper::modelTOdto).collect(Collectors.toList());
    }

    @Override
    public MutanteDTO getByName(String name) {
        log.info("[MutanteService(getByName)] -> Buscando por el nombre");

        var mutanteModel = mutantePort.getByName(name);

        return mutanteDTOMaper.modelTOdto(mutanteModel.get());
    }
}
