package com.jobsity.challenge.domain;

import lombok.Data;

/**
 * Score
 */
@Data
public class Score {

    private Player player;
    private int value;
    private boolean isFoul;

    public Score(Player player, int value, boolean isFoul) {
        this.player = player;
        this.value = value;
        this.isFoul = isFoul;
    }

    public Score(int value, boolean isFoul) {
        this.value = value;
        this.isFoul = isFoul;
    }

}