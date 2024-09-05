package com.projects.xmen_adn.infrastructure.adapter.entityes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PERSONAS")
public class PersonaEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_seq")
    @SequenceGenerator(name = "persona_seq", sequenceName = "persona_sequence", allocationSize = 1, initialValue = 1)
    private Long personaId;

    @Column(name = "NOMBRE", length = 15, nullable = false)
    private String nombre;

    @Column(name = "APELLIDO", length = 15, nullable = false)
    private String apellido;

}
