package com.mercadolibre.mutant.Controller;

import com.mercadolibre.mutant.Entity.DNAStats;
import com.mercadolibre.mutant.Service.StatsService;
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
