package com.jobsity.challenge.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * Scoreboard
 */
@Data
public class Scoreboard {

    private Map<Player, List<Frame>> scoreboard;

    public Scoreboard() {
        this.scoreboard = new HashMap<>();
    }

    public Scoreboard(Map<Player, List<Frame>> scoreboard) {
        this.scoreboard = scoreboard;
    }

}