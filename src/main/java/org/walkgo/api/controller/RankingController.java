package org.walkgo.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.walkgo.api.dto.RankingResponse;
import org.walkgo.api.service.RankingService;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping
    public ResponseEntity<List<RankingResponse>> getRanking() {
        return ResponseEntity.ok(rankingService.getRanking());
    }
}