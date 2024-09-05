package com.projects.xmen_adn.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonaModel {

    private Long personaId;

    private String nombre;

    private String apellido;

}
