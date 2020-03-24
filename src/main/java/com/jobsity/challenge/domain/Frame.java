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

}