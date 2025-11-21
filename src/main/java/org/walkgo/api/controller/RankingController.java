package org.walkgo.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.walkgo.api.model.RankingEntry;
import org.walkgo.api.service.RankingService;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/semana")
    public List<RankingEntry> getRankingSemana() {
        return rankingService.getRankingSemana();
    }
}