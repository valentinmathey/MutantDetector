package com.mercadolibre.mutant.controller;

import com.mercadolibre.mutant.exception.InvalidDNAException;
import com.mercadolibre.mutant.dto.DNARequest;
import com.mercadolibre.mutant.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// Importación adicional para manejar excepciones
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping("/")
    public ResponseEntity<String> isMutant(@RequestBody DNARequest dnaRequest) {
        boolean isMutant = mutantService.isMutant(dnaRequest.getDna());

        if (isMutant) {
            return ResponseEntity.ok().build(); // HTTP 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // HTTP 403 Forbidden
        }
    }

    // Manejo de otras excepciones

    @ExceptionHandler(InvalidDNAException.class)
    public ResponseEntity<String> handleInvalidDNAException(InvalidDNAException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); // HTTP 400 Bad Request
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor."); // HTTP 500 Internal Server Error
    }
}
