package com.mercadolibre.mutant.repository;

import com.mercadolibre.mutant.entity.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends JpaRepository<Mutant, Long> {
    boolean existsByDnaSequence(String dnaSequence);
    Mutant findByDnaSequence(String dnaSequence);

    // Contar mutantes
    @Query("SELECT COUNT(m) FROM Mutant m WHERE m.isMutant = true")
    Long countMutants();

    // Contar humanos
    @Query("SELECT COUNT(m) FROM Mutant m WHERE m.isMutant = false")
    Long countHumans();
}
