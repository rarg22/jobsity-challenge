package com.jobsity.challenge.repository;

import lombok.Data;

/**
 * ShotDto
 */
@Data
public class ScoreDto {

    private String player;
    private String value;

   public ScoreDto(String player, String value) {
        this.player = player;
        this.value = value;//
    }
}