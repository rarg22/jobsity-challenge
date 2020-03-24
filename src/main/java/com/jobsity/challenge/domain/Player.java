package com.jobsity.challenge.domain;

import lombok.Data;

/**
 * Player
 */
@Data
public class Player {

    public Player(String name) {
        this.name = name;
    }

    private String name;

}