package com.jobsity.challenge.service;

import java.util.List;

import com.jobsity.challenge.domain.Frame;

/**
 * ScoreCalculatorService
 */
public interface ScoreCalculatorService {

    public void calculateFrameScores(List<Frame> frames);
    
}