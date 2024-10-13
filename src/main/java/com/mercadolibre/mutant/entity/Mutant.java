package com.mercadolibre.mutant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mutant")
public class Mutant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dna_sequence", nullable = false, unique = true, length = 1000)
    private String dnaSequence;

    @Column(name = "is_mutant", nullable = false)
    private boolean isMutant;
}