package com.projects.xmen_adn.infrastructure.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.xmen_adn.aplication.useCases.EstadisticasUseCase;
import com.projects.xmen_adn.domain.model.dto.EstadisticaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Estadisticas Info")
@RequestMapping("/")
public class EstadisticasController {

    @Autowired
    private EstadisticasUseCase estadisticasUseCase;

    @Operation(summary = "Estadisticas del servicio")
    @GetMapping("/stats")
    public EstadisticaDTO getEstadistica() {
        return estadisticasUseCase.getEstadistica();
    }

}
