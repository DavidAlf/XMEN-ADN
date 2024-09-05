package com.projects.xmen_adn.aplication.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.xmen_adn.aplication.mapper.dto.PersonaDTOMaper;
import com.projects.xmen_adn.aplication.mapper.request.PersonaRequestMapper;
import com.projects.xmen_adn.aplication.useCases.PersonaUseCase;
import com.projects.xmen_adn.domain.model.dto.PersonaDTO;
import com.projects.xmen_adn.domain.model.request.PersonaRequest;
import com.projects.xmen_adn.domain.port.PersonaPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonaService implements PersonaUseCase {

    @Autowired
    private PersonaPort personaPort;

    @Autowired
    private PersonaRequestMapper personaRequestMapper;

    @Autowired
    private PersonaDTOMaper personaDTOMaper;

    @Override
    public PersonaDTO save(PersonaRequest personaRequest) {
        log.info("[PersonaService(save)] -> Guardando");

        var personaModel = personaRequestMapper.requestTOmodel(personaRequest);
        var personaModelSave = personaPort.save(personaModel);

        return personaDTOMaper.modelTOdto(personaModelSave);
    }

    @Override
    public PersonaDTO update(Long id, PersonaRequest personaRequest) {
        log.info("[PersonaService(update)] -> Actualizando");

        var personaModel = personaRequestMapper.requestTOmodel(personaRequest);

        var personaModelUpdate = personaPort.update(id, personaModel);

        return personaDTOMaper.modelTOdto(personaModelUpdate);
    }

    @Override
    public void delete(Long id) {
        log.info("[PersonaService(delete)] -> Eliminando");

        personaPort.delete(id);
    }

    @Override
    public List<PersonaDTO> list() {
        log.info("[PersonaService(list)] -> Listando");

        var personaModel = personaPort.list();

        return personaModel.stream().map(personaDTOMaper::modelTOdto).collect(Collectors.toList());
    }

    @Override
    public PersonaDTO getByName(String name) {
        log.info("[PersonaService(getByName)] -> Buscando por el nombre");

        var personaModel = personaPort.getByName(name);

        return personaDTOMaper.modelTOdto(personaModel.get());
    }
}
