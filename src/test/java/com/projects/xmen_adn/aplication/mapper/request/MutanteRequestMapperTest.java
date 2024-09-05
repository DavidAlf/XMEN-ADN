package com.projects.xmen_adn.aplication.mapper.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.projects.xmen_adn.domain.model.MutanteModel;
import com.projects.xmen_adn.domain.model.request.MutanteRequest;

public class MutanteRequestMapperTest {

    @InjectMocks
    private MutanteRequestMapper mutanteRequestMapper;

    private MutanteRequest mutanteRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

        mutanteRequest = MutanteRequest.builder()
                .nombre("David")
                .apellido("Alfonso")
                .adn(dna)
                .build();
    }

    @Test
    void testClienteRequestTOclienteModel() {
        // > When
        MutanteModel model = mutanteRequestMapper.requestTOmodel(mutanteRequest);

        // > Then
        assertThat(model).isNotNull();
        assertThat(model.getMutanteId()).isNull();
        assertThat(model.getNombre()).isEqualTo(mutanteRequest.getNombre());
        assertThat(model.getApellido()).isEqualTo(mutanteRequest.getApellido());
        assertThat(model.getAdn()).isEqualTo(mutanteRequest.getAdn());

    }
}
