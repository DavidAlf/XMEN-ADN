package com.projects.xmen_adn.infrastructure.adapter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.projects.xmen_adn.infrastructure.adapter.entityes.PersonaEntity;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

    Optional<PersonaEntity> findByNombre(@Param(("nombre")) String nombre);

}