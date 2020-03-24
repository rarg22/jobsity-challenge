package com.jobsity.challenge.service;

import com.jobsity.challenge.domain.Scoreboard;

/**
 * ScoreboardService
 */
public interface ScoreboardService {

    public void print(Scoreboard scoreboard);

    public Scoreboard getScoreboard();

}