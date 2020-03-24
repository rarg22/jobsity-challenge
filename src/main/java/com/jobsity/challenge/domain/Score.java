package com.jobsity.challenge.domain;

import lombok.Data;

/**
 * Score
 */
@Data
public class Score {

    private int value;
    private boolean isFoul;

    public Score(int value, boolean isFoul){
        this.value = value;
        this.isFoul = isFoul;
    }
    
}