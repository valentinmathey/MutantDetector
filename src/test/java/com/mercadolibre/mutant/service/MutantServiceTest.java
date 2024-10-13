package com.mercadolibre.mutant.service;

import com.mercadolibre.mutant.exception.InvalidDNAException;
import com.mercadolibre.mutant.repository.MutantRepository;
import org.junit.jupiter.api.*;
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

    // Este método se ejecuta una sola vez antes de todas las pruebas
    @BeforeAll
    static void setUpBeforeAll() {
        System.out.println("Ejecutando setUpBeforeAll - Inicialización global");
    }

    // Este método se ejecuta antes de cada prueba individual
    @BeforeEach
    void setUpBeforeEach() {
        System.out.println("Ejecutando setUpBeforeEach - Preparando la prueba");
    }

    // Este método se ejecuta después de cada prueba individual
    @AfterEach
    void tearDownAfterEach() {
        System.out.println("Ejecutando tearDownAfterEach - Limpiando después de la prueba");
    }

    // Este método se ejecuta una sola vez después de que todas las pruebas hayan finalizado
    @AfterAll
    static void tearDownAfterAll() {
        System.out.println("Ejecutando tearDownAfterAll - Finalización global");
    }

    // Aquí van tus pruebas

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

    @Test
    void testEmptyDna() {
        String[] emptyDna = {};

        Exception exception = assertThrows(InvalidDNAException.class, () -> {
            mutantService.isMutant(emptyDna);
        });

        String expectedMessage = "La secuencia de ADN está vacía.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testNonSquareDna() {
        String[] nonSquareDna = {"ATGCGA", "CAGTGC", "TTATGT"}; // Matriz no cuadrada

        Exception exception = assertThrows(InvalidDNAException.class, () -> {
            mutantService.isMutant(nonSquareDna);
        });

        String expectedMessage = "La secuencia de ADN no es una matriz cuadrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // ===========================================
    // Pruebas con las secuencias mutantes
    @Test
    void testMutant1() {
        String[] dna = {"ATGCGA", "CCCCCC", "TTATGT", "TTTTTT", "CCCCTA", "TCACTG"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(dna);

        assertTrue(result);
    }

    @Test
    void testMutant2() {
        String[] dna = {"GAAAAC", "AGAACA", "AAGACA", "AAAGCA", "AAAAGC", "CAAAAG"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(dna);

        assertTrue(result);
    }

    @Test
    void testMutant3() {
        String[] dna = {"ATGCGA", "CAGTGC", "ATTTTA", "AGAAGG", "ACCCCC", "TCACTG"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(dna);

        assertTrue(result);
    }

    @Test
    void testMutant4() {
        String[] dna = {"AGGGGC", "AGGGGC", "AGGGGC", "AGGGGC", "AGGGGC", "AGGGGC"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(dna);

        assertTrue(result);
    }

    @Test
    void testMutant5() {
        String[] dna = {"AAAAAT", "AAAATA", "AAATAA", "AATAAA", "ATAAAA", "TAAAAA"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(dna);

        assertTrue(result);
    }

    // ===========================================
    // Pruebas con las secuencias no mutantes
    @Test
    void testNonMutant1() {
        String[] dna = {"AGTCGA", "CTGTAC", "TATTGT", "AGACGG", "GCGTCA", "TCACTG"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(dna);

        assertFalse(result);
    }

    @Test
    void testNonMutant2() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGACGT", "GCGTCA", "TCACTG"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(dna);

        assertFalse(result);
    }

    @Test
    void testNonMutant3() {
        String[] dna = {"ATGCGG", "CAGTGC", "TTATGT", "AGACGG", "GCGTCA", "TCACTG"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(dna);

        assertFalse(result);
    }

    @Test
    void testNonMutant4() {
        String[] dna = {"AAGCTA", "CTGTAG", "TATTGA", "AGACGG", "GCGTCA", "TCACTG"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(dna);

        assertFalse(result);
    }

    @Test
    void testNonMutant5() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGACGG", "GCGTCA", "TCACAG"};
        Mockito.when(mutantRepository.existsByDnaSequence(Mockito.anyString())).thenReturn(false);

        boolean result = mutantService.isMutant(dna);

        assertFalse(result);
    }
}
