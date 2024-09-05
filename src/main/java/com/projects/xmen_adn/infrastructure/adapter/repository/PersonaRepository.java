package com.projects.xmen_adn.infrastructure.adapter.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projects.xmen_adn.infrastructure.adapter.entityes.PersonaEntity;

@EnableScan
@Repository
public interface PersonaRepository extends CrudRepository<PersonaEntity, String> {

    Optional<PersonaEntity> findByNombre(@Param(("nombre")) String nombre);

}