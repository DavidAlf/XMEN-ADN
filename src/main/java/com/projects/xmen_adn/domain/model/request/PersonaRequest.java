package com.projects.xmen_adn.domain.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonaRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String apellido;

    @NotNull(message = "El ADN no puede ser nulo")
    @NotEmpty(message = "El ADN no puede estar vacio")
    private String[] adn;

}
