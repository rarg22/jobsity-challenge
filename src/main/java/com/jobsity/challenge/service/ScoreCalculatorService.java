package com.jobsity.challenge.service;

import java.util.List;

import com.jobsity.challenge.domain.Frame;

/**
 * ScoreCalculatorService
 */
public interface ScoreCalculatorService {

    public int process(List<Frame> frames);

    public int processFrame(Frame frame);
}