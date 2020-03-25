package com.jobsity.challenge.service.impl;

import java.util.List;

import com.jobsity.challenge.domain.Frame;
import com.jobsity.challenge.service.ScoreCalculatorService;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * ScoreCalculatorServiceImpl
 */
@Service
@Primary
public class ScoreCalculatorServiceImpl implements ScoreCalculatorService {
    @Override
    public void calculateFrameScores(List<Frame> frames) {
        int nextFrameId = 1;
        int totalScore = 0;
        for (Frame frame : frames) {
            int totalScorePerFrame = 0;
            if (frame.getId() != 10) {
                // if only 1 score, assume its a strike
                if (frame.getScores().size() == 1) {
                    totalScorePerFrame = lookAheadWhenStrike(frame, frames, nextFrameId);
                }
                // if 2 scores are found, check if Spare or not
                if (frame.getScores().size() == 2) {
                    if (checkIfSpare(frame)) {
                        totalScorePerFrame = lookAheadWhenSpare(frame, frames, nextFrameId);
                    } else {
                        totalScorePerFrame += frame.getScores().get(0).getValue();
                        totalScorePerFrame += frame.getScores().get(1).getValue();
                    }
                }
            } else {
                // add all 3 frame scores
                totalScorePerFrame += frame.getScores().get(0).getValue();
                totalScorePerFrame += frame.getScores().get(1).getValue();
                totalScorePerFrame += frame.getScores().get(2).getValue();
            }
            // set final score for the current frame
            totalScore += totalScorePerFrame;
            frame.setTotalScore(totalScore);
            nextFrameId++;
        }

    }

    private int lookAheadWhenStrike(Frame frame, List<Frame> frames, Integer nextFrameId) {
        int totalScore = 0;
        // get current frame strike score
        totalScore += frame.getScores().get(0).getValue();
        // search next
        Frame nextFrame = frames.get(nextFrameId);
        if (nextFrame.getScores().size() >= 2) {
            // add both scores
            totalScore += nextFrame.getScores().get(0).getValue();
            totalScore += nextFrame.getScores().get(1).getValue();
        } else {
            // add nextFrame only score
            totalScore += nextFrame.getScores().get(0).getValue();
            // search frame after next and get the first score found
            Frame afterNextFrame = frames.get(nextFrameId + 1);
            totalScore += afterNextFrame.getScores().get(0).getValue();
        }
        return totalScore;
    }

    private int lookAheadWhenSpare(Frame frame, List<Frame> frames, Integer nextFrameId) {
        int totalScore = 0;
        // get current frame strike score
        totalScore += frame.getScores().get(0).getValue();
        totalScore += frame.getScores().get(1).getValue();
        // search next
        Frame nextFrame = frames.get(nextFrameId);
        // add first score
        totalScore += nextFrame.getScores().get(0).getValue();

        return totalScore;
    }

    private boolean checkIfSpare(Frame frame) {
        return frame.getScores().get(0).getValue() + frame.getScores().get(1).getValue() == 10;
    }

}