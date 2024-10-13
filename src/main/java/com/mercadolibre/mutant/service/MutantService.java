package com.mercadolibre.mutant.service;

import com.mercadolibre.mutant.entity.Mutant;
import com.mercadolibre.mutant.exception.InvalidDNAException;
import com.mercadolibre.mutant.repository.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class MutantService {

    @Autowired
    private MutantRepository mutantRepository;

    public boolean isMutant(String[] dna) {
        // Validamos que el ADN sea correcto
        validateDNA(dna);

        // Normalizamos el ADN a mayúsculas (opcional pero útil para asegurar consistencia)
        for (int i = 0; i < dna.length; i++) {
            dna[i] = dna[i].toUpperCase();
        }

        // Convertimos el array de ADN en una cadena única para almacenamiento
        String dnaSequence = String.join("", dna);

        // Verificar si esta secuencia de ADN ya fue analizada previamente
        if (mutantRepository.existsByDnaSequence(dnaSequence)) {
            // Recuperamos el registro existente
            Mutant existingRecord = mutantRepository.findByDnaSequence(dnaSequence);

            if (existingRecord.isMutant()) {
                System.out.println("Mutante ya registrado.");
            } else {
                System.out.println("No mutante ya registrado.");
            }

            return existingRecord.isMutant();
        }

        // Convertir el array de ADN en una matriz para análisis
        char[][] matrix = convertToMatrix(dna);

        // Medir tiempo de los métodos (opcional)
        long startOptimized = System.nanoTime();
        boolean isMutantOptimized = hasSequenceOptimized(matrix);
        long endOptimized = System.nanoTime();
        long durationOptimized = endOptimized - startOptimized;

        // Imprimir tiempo de ejecución (opcional)
        System.out.println("Tiempo Optimizado: " + durationOptimized + " ns");

        // Método principal de detección de mutantes
        boolean isMutant = hasSequenceOptimized(matrix);

        // Guardar el resultado en la base de datos
        Mutant dnaRecord = new Mutant(null, dnaSequence, isMutant);
        mutantRepository.save(dnaRecord);

        // Retornamos el resultado de si es o no mutante
        return isMutant;
    }

    // Validamos que la secuencia de ADN sea correcta (debe ser cuadrada, no ser nula y contener solo caracteres A, T, C, G)
    private void validateDNA(String[] dna) {
        if (dna == null || dna.length == 0) {
            throw new InvalidDNAException("La secuencia de ADN está vacía.");
        }

        int n = dna.length;
        Pattern pattern = Pattern.compile("^[ATCG]+$"); // Solo permite A, T, C, G

        for (String row : dna) {
            // Verificamos que cada fila tenga la misma longitud (cuadrada)
            if (row.length() != n) {
                throw new InvalidDNAException("La secuencia de ADN no es una matriz cuadrada.");
            }
            // Verificamos que solo contenga caracteres válidos
            if (!pattern.matcher(row).matches()) {
                throw new InvalidDNAException("La secuencia de ADN contiene caracteres inválidos. Solo se permiten A, T, C, G.");
            }
        }
    }

    // Convierte el array de ADN en una matriz de caracteres para facilitar el análisis
    private char[][] convertToMatrix(String[] dna) {
        int n = dna.length;
        char[][] matrix = new char[n][n];

        // Rellenamos la matriz con las filas de ADN
        for (int i = 0; i < n; i++) {
            matrix[i] = dna[i].toCharArray();
        }
        return matrix;
    }

    // Método optimizado para detectar secuencias mutantes en la matriz
    private boolean hasSequenceOptimized(char[][] matrix) {
        int n = matrix.length;
        int[][][] dp = new int[n][n][4]; // Arreglo 3D: 0: horizontal, 1: vertical, 2: diagonal principal, 3: diagonal secundaria
        int sequenceCount = 0; // Contador para detectar más de una secuencia mutante

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char current = matrix[i][j]; // Caracter actual

                // Verificamos secuencias horizontales
                dp[i][j][0] = (j > 0 && current == matrix[i][j - 1]) ? dp[i][j - 1][0] + 1 : 1;
                if (dp[i][j][0] >= 4) {
                    sequenceCount++;
                    if (sequenceCount > 1) return true;
                }

                // Verificamos secuencias verticales
                dp[i][j][1] = (i > 0 && current == matrix[i - 1][j]) ? dp[i - 1][j][1] + 1 : 1;
                if (dp[i][j][1] >= 4) {
                    sequenceCount++;
                    if (sequenceCount > 1) return true;
                }

                // Verificamos secuencias en la diagonal principal
                dp[i][j][2] = (i > 0 && j > 0 && current == matrix[i - 1][j - 1]) ? dp[i - 1][j - 1][2] + 1 : 1;
                if (dp[i][j][2] >= 4) {
                    sequenceCount++;
                    if (sequenceCount > 1) return true;
                }

                // Verificamos secuencias en la diagonal secundaria
                dp[i][j][3] = (i > 0 && j < n - 1 && current == matrix[i - 1][j + 1]) ? dp[i - 1][j + 1][3] + 1 : 1;
                if (dp[i][j][3] >= 4) {
                    sequenceCount++;
                    if (sequenceCount > 1) return true;
                }
            }
        }
        // Retornamos falso si no se encontraron suficientes secuencias mutantes
        return false;
    }

}

