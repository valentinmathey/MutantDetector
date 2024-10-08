package com.mercadolibre.mutant.Service;

import com.mercadolibre.mutant.Exception.InvalidDNAException;
import com.mercadolibre.mutant.Repository.MutantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MutantServiceTest {

    @Mock
    private MutantRepository mutantRepository;

    @InjectMocks
    private MutantService mutantService;

    @Test
    void testIsMutant() {
        String[] mutantDna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(mutantDna);

        assertTrue(result);
    }

    @Test
    void testIsNotMutant() {
        String[] humanDna = {"ATGCGA", "CAGTGC", "TTATGT", "AGACGG", "GCGTCA", "TCACTG"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(humanDna);

        assertFalse(result);
    }

    @Test
    void testInvalidDna() {
        String[] invalidDna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCXTA", "TCACTG"};

        Exception exception = assertThrows(InvalidDNAException.class, () -> {
            mutantService.isMutant(invalidDna);
        });

        String expectedMessage = "La secuencia de ADN contiene caracteres inválidos. Solo se permiten A, T, C, G.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
