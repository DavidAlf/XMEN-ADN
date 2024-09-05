package com.projects.xmen_adn.infrastructure.adapter;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.projects.xmen_adn.domain.model.EstadisticaModel;
import com.projects.xmen_adn.domain.port.EstadisticaPort;
import com.projects.xmen_adn.infrastructure.adapter.entityes.MutanteEntity;
import com.projects.xmen_adn.infrastructure.adapter.repository.MutanteRepository;
import com.projects.xmen_adn.infrastructure.config.util.Adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class EstadisticaAdapter implements EstadisticaPort {

    @Autowired
    private MutanteRepository mutanteRepository;

    @Override
    public Optional<EstadisticaModel> getEstadistica() {
        log.info("[MutanteAdapter(list)] -> Listando ");

        var mutanteEntity = StreamSupport.stream(mutanteRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        long countMutantDna = mutanteEntity.stream()
                .filter(MutanteEntity::isMutante)
                .count();

        long countHumanDna = mutanteEntity.size() - countMutantDna;

        float ratio = (countHumanDna > 0) ? (float) countMutantDna / countHumanDna : countMutantDna;

        return Optional.of(EstadisticaModel.builder()
                .count_human_dna(String.valueOf(countMutantDna))
                .count_mutant_dna(String.valueOf(countHumanDna))
                .ratio(String.valueOf(ratio))
                .build());
    }

}
