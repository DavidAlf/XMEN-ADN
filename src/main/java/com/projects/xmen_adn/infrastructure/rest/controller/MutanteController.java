package com.projects.xmen_adn.infrastructure.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.xmen_adn.aplication.useCases.MutanteUseCase;
import com.projects.xmen_adn.domain.model.dto.MutanteDTO;
import com.projects.xmen_adn.domain.model.request.MutanteRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Mutante Info")
@RequestMapping("/mutant")
public class MutanteController {

    @Autowired
    private MutanteUseCase mutanteUseCase;

    @PostMapping
    @Operation(summary = "Crea Mutantes")
    public MutanteDTO save(@Valid @RequestBody MutanteRequest mutanteRequest) {
        return mutanteUseCase.save(mutanteRequest);
    }

    @Operation(summary = "Actualiza Mutantes")
    @PutMapping("/{id}")
    public MutanteDTO update(@Valid @PathVariable String id, @Valid @RequestBody MutanteRequest mutanteRequest) {
        return mutanteUseCase.update(id, mutanteRequest);
    }

    @Operation(summary = "Elimina Mutantes")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        mutanteUseCase.delete(id);
    }

    @Operation(summary = "Lista Mutantes")
    @GetMapping
    public List<MutanteDTO> List() {
        return mutanteUseCase.list();
    }

    @Operation(summary = "Busca Mutantes por el nombre")
    @GetMapping("/find")
    public MutanteDTO getByName(
            @RequestParam(name = "nombre", required = true) String nombre) {
        return mutanteUseCase.getByName(nombre);
    }
}
