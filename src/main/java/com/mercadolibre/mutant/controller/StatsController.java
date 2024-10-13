package com.mercadolibre.mutant.controller;

import com.mercadolibre.mutant.dto.DNAStats;
import com.mercadolibre.mutant.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    public DNAStats getStats() {
        return statsService.getStats();
    }
}
