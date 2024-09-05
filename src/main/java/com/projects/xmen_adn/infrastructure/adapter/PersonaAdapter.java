package com.projects.xmen_adn.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.projects.xmen_adn.domain.logic.MutanteLogic;
import com.projects.xmen_adn.domain.model.PersonaModel;
import com.projects.xmen_adn.domain.model.constants.PersonaConstant;
import com.projects.xmen_adn.domain.port.PersonaPort;
import com.projects.xmen_adn.infrastructure.adapter.entityes.PersonaEntity;
import com.projects.xmen_adn.infrastructure.adapter.exception.ApiException;
import com.projects.xmen_adn.infrastructure.adapter.mapper.PersonaMapper;
import com.projects.xmen_adn.infrastructure.adapter.repository.PersonaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonaAdapter implements PersonaPort {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private MutanteLogic mutanteLogic;

    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public PersonaModel save(PersonaModel personaModel) {
        log.info("[PersonaAdapter(save)] -> Guardando ");

        var personaEntity = personaMapper.modelTOentity(personaModel);

        var isMutante = mutanteLogic.isMutante(personaEntity.getAdn());
        personaEntity.setMutante(isMutante);

        var personaEntitySaved = personaRepository.save(personaEntity);

        return personaMapper.entityTOmodel(personaEntitySaved);
    }

    @Override
    public PersonaModel update(String id, PersonaModel personaModel) {
        log.info("[PersonaAdapter(update)] -> Actualizando ");

        var personaEntity = personaMapper.modelTOentity(personaModel);

        var personaEntityUpdate = personaRepository.findById(id)
                .map(item -> {
                    item.setPersonaId(id);
                    item.setNombre(personaEntity.getNombre());
                    item.setApellido(personaEntity.getApellido());
                    item.setMutante(mutanteLogic.isMutante(personaEntity.getAdn()));

                    return personaRepository.save(item);
                })
                .orElse(PersonaEntity.builder().build());

        return personaMapper.entityTOmodel(personaEntityUpdate);
    }

    @Override
    public void delete(String id) {
        log.info("[PersonaAdapter(delete)] -> Eliminando ");

        String msn = personaRepository.findById(id).map(
                personaDeleting -> {
                    personaRepository.delete(personaDeleting);

                    return "Eliminado con exito";
                }).orElse("Error eliminando");

        log.info(msn);
    }

    @Override
    public List<PersonaModel> list() {
        log.info("[PersonaAdapter(list)] -> Listando ");

        var personaEntity = StreamSupport.stream(personaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return personaEntity.stream().map(personaMapper::entityTOmodel).collect(Collectors.toList());
    }

    @Override
    public Optional<PersonaModel> getByName(String nombre) {
        log.info("[PersonaAdapter(getByName)] -> Buscando por el nombre");

        try {
            var optionalPersona = personaRepository.findByNombre(nombre);

            if (optionalPersona == null || optionalPersona.isEmpty()) {
                throw new ApiException(HttpStatus.NOT_FOUND,
                        String.format(PersonaConstant.CURRENT_NOT_PRESENT, nombre));
            }

            Optional<PersonaModel> personaModel = Optional
                    .of(personaMapper.entityTOmodel(optionalPersona.get()));

            return personaModel;
        } catch (Exception e) {
            log.error(String.format(PersonaConstant.CURRENT_NOT_PRESENT, nombre), e.getMessage());
            return Optional.of(PersonaModel.builder().build());
        }

    }

    @Override
    public Optional<PersonaModel> getById(String id) {
        log.info("[PersonaAdapter(getById)] -> Buscando por el id");

        try {
            var optionalPersona = personaRepository.findById(id);

            if (!optionalPersona.isPresent()) {
                throw new ApiException(HttpStatus.NOT_FOUND,
                        String.format(PersonaConstant.CURRENT_NOT_PRESENT, id));
            }

            Optional<PersonaModel> personaModel = Optional
                    .of(personaMapper.entityTOmodel(optionalPersona.get()));

            return personaModel;
        } catch (Exception e) {
            log.error(String.format(PersonaConstant.CURRENT_NOT_PRESENT, id), e.getMessage());
            return Optional.of(PersonaModel.builder().build());
        }
    }

}
