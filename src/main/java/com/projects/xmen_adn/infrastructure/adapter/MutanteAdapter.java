package com.projects.xmen_adn.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.projects.xmen_adn.domain.logic.DetectarMutanteLogic;
import com.projects.xmen_adn.domain.model.MutanteModel;
import com.projects.xmen_adn.domain.model.constants.MutanteConstant;
import com.projects.xmen_adn.domain.port.MutantePort;
import com.projects.xmen_adn.infrastructure.adapter.entityes.MutanteEntity;
import com.projects.xmen_adn.infrastructure.adapter.exception.ApiException;
import com.projects.xmen_adn.infrastructure.adapter.mapper.MutanteMapper;
import com.projects.xmen_adn.infrastructure.adapter.repository.MutanteRepository;
import com.projects.xmen_adn.infrastructure.config.util.Adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class MutanteAdapter implements MutantePort {

    @Autowired
    private MutanteRepository mutanteRepository;

    @Autowired
    private DetectarMutanteLogic detectarMutanteLogic;

    @Autowired
    private MutanteMapper mutanteMapper;

    @Override
    public MutanteModel save(MutanteModel mutanteModel) {
        log.info("[MutanteAdapter(save)] -> Guardando ");

        var mutanteEntity = mutanteMapper.modelTOentity(mutanteModel);

        var isMutante = detectarMutanteLogic.isMutante(mutanteEntity.getAdn());
        mutanteEntity.setMutante(isMutante);

        var mutanteEntitySaved = mutanteRepository.save(mutanteEntity);

        return mutanteMapper.entityTOmodel(mutanteEntitySaved);
    }

    @Override
    public MutanteModel update(String id, MutanteModel mutanteModel) {
        log.info("[MutanteAdapter(update)] -> Actualizando ");

        var mutanteEntity = mutanteMapper.modelTOentity(mutanteModel);

        var mutanteEntityUpdate = mutanteRepository.findById(id)
                .map(item -> {
                    item.setMutanteId(id);
                    item.setNombre(mutanteEntity.getNombre());
                    item.setApellido(mutanteEntity.getApellido());
                    item.setMutante(detectarMutanteLogic.isMutante(mutanteEntity.getAdn()));

                    return mutanteRepository.save(item);
                })
                .orElse(MutanteEntity.builder().build());

        return mutanteMapper.entityTOmodel(mutanteEntityUpdate);
    }

    @Override
    public void delete(String id) {
        log.info("[MutanteAdapter(delete)] -> Eliminando ");

        String msn = mutanteRepository.findById(id).map(
                mutanteDeleting -> {
                    mutanteRepository.delete(mutanteDeleting);

                    return "Eliminado con exito";
                }).orElse("Error eliminando");

        log.info(msn);
    }

    @Override
    public List<MutanteModel> list() {
        log.info("[MutanteAdapter(list)] -> Listando ");

        var mutanteEntity = StreamSupport.stream(mutanteRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return mutanteEntity.stream().map(mutanteMapper::entityTOmodel).collect(Collectors.toList());
    }

    @Override
    public Optional<MutanteModel> getByName(String nombre) {
        log.info("[MutanteAdapter(getByName)] -> Buscando por el nombre");

        try {
            var optionalMutante = mutanteRepository.findByNombre(nombre);

            if (optionalMutante == null || optionalMutante.isEmpty()) {
                throw new ApiException(HttpStatus.NOT_FOUND,
                        String.format(MutanteConstant.CURRENT_NOT_PRESENT, nombre));
            }

            Optional<MutanteModel> mutanteModel = Optional
                    .of(mutanteMapper.entityTOmodel(optionalMutante.get()));

            return mutanteModel;
        } catch (Exception e) {
            log.error(String.format(MutanteConstant.CURRENT_NOT_PRESENT, nombre), e.getMessage());
            return Optional.of(MutanteModel.builder().build());
        }

    }

    @Override
    public Optional<MutanteModel> getById(String id) {
        log.info("[MutanteAdapter(getById)] -> Buscando por el id");

        try {
            var optionalMutante = mutanteRepository.findById(id);

            if (!optionalMutante.isPresent()) {
                throw new ApiException(HttpStatus.NOT_FOUND,
                        String.format(MutanteConstant.CURRENT_NOT_PRESENT, id));
            }

            Optional<MutanteModel> mutanteModel = Optional
                    .of(mutanteMapper.entityTOmodel(optionalMutante.get()));

            return mutanteModel;
        } catch (Exception e) {
            log.error(String.format(MutanteConstant.CURRENT_NOT_PRESENT, id), e.getMessage());
            return Optional.of(MutanteModel.builder().build());
        }
    }

}
