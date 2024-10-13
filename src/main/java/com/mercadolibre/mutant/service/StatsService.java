package com.mercadolibre.mutant.service;

import com.mercadolibre.mutant.dto.DNAStats;
import com.mercadolibre.mutant.repository.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    private MutantRepository mutantRepository;

    //@Cacheable(value = "dnaStats")
    public DNAStats getStats() {
        Long countMutants = mutantRepository.countMutants();
        Long countHumans = mutantRepository.countHumans();
        Double ratio = (countHumans == 0) ? 0.0 : (double) countMutants / countHumans;

        DNAStats stats = new DNAStats();
        stats.setCountMutantDna(countMutants);
        stats.setCountHumanDna(countHumans);
        stats.setRatio(ratio);

        return stats;
    }

}
