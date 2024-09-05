package com.projects.xmen_adn.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MutanteModel {

    private String mutanteId;

    private String nombre;

    private String apellido;

    private String[] adn;

    private boolean isMutante;

}
