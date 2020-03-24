package com.jobsity.challenge.repository;

import lombok.Data;

/**
 * ShotDto
 */
@Data
public class ScoreDto {

    private String player;
    private int score;
    private boolean isFoul;
    
}