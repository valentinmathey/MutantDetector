package com.mercadolibre.mutant.Service;

import com.mercadolibre.mutant.Entity.DNAStats;
import com.mercadolibre.mutant.Repository.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
