package com.jobsity.challenge.domain;

import java.util.List;

import lombok.Data;

/**
 * Frame
 */
@Data
public class Frame {

    private int id;
    private List<Score> scores;
    private Integer totalScore;

    public Frame(int id, List<Score> scores) {
        this.id = id;
        this.scores = scores;
    }

    public Frame(int id, List<Score> scores, Integer totalScore) {
        this.id = id;
        this.scores = scores;
        this.totalScore = totalScore;
    }

}