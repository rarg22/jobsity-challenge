package com.jobsity.challenge.service.impl;

import java.util.List;
import java.util.Map;

import com.jobsity.challenge.domain.Frame;
import com.jobsity.challenge.domain.Player;
import com.jobsity.challenge.domain.Score;
import com.jobsity.challenge.domain.Scoreboard;
import com.jobsity.challenge.repository.ScoreRepository;
import com.jobsity.challenge.service.ScoreCalculatorService;
import com.jobsity.challenge.service.ScoreSortingService;
import com.jobsity.challenge.service.ScoreboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ScoreboeardServiceImpl
 */
@Service
public class ScoreboardServiceImpl implements ScoreboardService {

    private ScoreRepository scoreRepository;
    private ScoreSortingService scoreSortingService;
    private ScoreCalculatorService scoreCalculatorService;

    @Autowired
    public ScoreboardServiceImpl(ScoreRepository scoreRepository, ScoreSortingService scoreSortingService,
            ScoreCalculatorService scoreCalculatorService) {
        this.scoreRepository = scoreRepository;
        this.scoreCalculatorService = scoreCalculatorService;
        this.scoreSortingService = scoreSortingService;
    }

    @Override
    public Scoreboard getScoreboard() {

        List<Score> scores = scoreRepository.getScores();
        Map<Player, List<Frame>> framesByPlayers = scoreSortingService.arrange(scores);
        for (Map.Entry<Player, List<Frame>> framesByPlayer : framesByPlayers.entrySet()) {
            scoreCalculatorService.calculateFrameScores(framesByPlayer.getValue());
        }
        return new Scoreboard(framesByPlayers);
    }

}