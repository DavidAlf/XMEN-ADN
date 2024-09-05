package com.projects.xmen_adn.domain.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EstadisticaDTO {

    private String count_mutant_dna;

    private String count_human_dna;

    private String ratio;

}
