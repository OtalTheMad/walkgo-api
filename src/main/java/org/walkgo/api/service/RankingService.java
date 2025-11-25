package org.walkgo.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.walkgo.api.model.RankingRow;
import org.walkgo.api.repository.RankingRepository;

import java.util.List;

@Service
public class RankingService {

    private final RankingRepository rankingRepository;

    public RankingService(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    @Transactional
    public List<RankingRow> getRanking() {
        rankingRepository.insertMissingUsers();
        rankingRepository.recomputePositions();
        return rankingRepository.findRanking();
    }
}