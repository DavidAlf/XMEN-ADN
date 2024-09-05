package com.projects.xmen_adn.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EstadisticaModel {

    private String count_mutant_dna;

    private String count_human_dna;

    private String ratio;

}
