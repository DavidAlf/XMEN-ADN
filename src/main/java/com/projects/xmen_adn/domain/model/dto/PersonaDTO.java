package com.projects.xmen_adn.domain.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonaDTO {

    private Long personaId;

    private String nombre;

    private String apellido;

}
