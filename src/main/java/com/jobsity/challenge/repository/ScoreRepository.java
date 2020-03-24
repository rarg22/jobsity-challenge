package com.jobsity.challenge.repository;

import java.util.List;

import com.jobsity.challenge.domain.Score;

/**
 * ScoreRepository
 */
public interface ScoreRepository {

    public List<Score> getScores();

}