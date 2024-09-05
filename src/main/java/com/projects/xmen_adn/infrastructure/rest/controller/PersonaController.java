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

import com.projects.xmen_adn.aplication.useCases.PersonaUseCase;
import com.projects.xmen_adn.domain.model.dto.PersonaDTO;
import com.projects.xmen_adn.domain.model.request.PersonaRequest;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaUseCase personaUseCase;

    @PostMapping
    public PersonaDTO save(@RequestBody PersonaRequest personaRequest) {
        return personaUseCase.save(personaRequest);
    }

    @PutMapping("/{id}")
    public PersonaDTO update(@PathVariable String id, @RequestBody PersonaRequest personaRequest) {
        return personaUseCase.update(id, personaRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        personaUseCase.delete(id);
    }

    @GetMapping
    public List<PersonaDTO> List() {
        return personaUseCase.list();
    }

    @GetMapping("/find")
    public PersonaDTO getByName(
            @RequestParam(name = "nombre", required = true) String nombre) {
        return personaUseCase.getByName(nombre);
    }
}
