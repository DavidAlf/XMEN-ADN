package com.projects.xmen_adn.domain.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonaRequest {

    private String nombre;

    private String apellido;
}
