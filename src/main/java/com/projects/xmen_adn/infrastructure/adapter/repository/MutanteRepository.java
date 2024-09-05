package com.projects.xmen_adn.infrastructure.adapter.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projects.xmen_adn.infrastructure.adapter.entityes.MutanteEntity;

@EnableScan
@Repository
public interface MutanteRepository extends CrudRepository<MutanteEntity, String> {

    Optional<MutanteEntity> findByNombre(@Param(("nombre")) String nombre);

}