package com.jobsity.challenge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.jobsity.challenge.domain.Frame;
import com.jobsity.challenge.domain.Score;
import com.jobsity.challenge.service.ScoreCalculatorService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ScoreCalculatorServiceTest
 */
@SpringBootTest
public class ScoreCalculatorServiceTests {

    @Autowired
    private ScoreCalculatorService scoreCalculatorService;

    @Test
    public void TestCalculateFrameScoresGivenSampleInput() {
        List<Frame> sampleFrameListInput = getSampleFrameListInput();

        List<Frame> expected = getExpectedFrameListOutput();
        scoreCalculatorService.calculateFrameScores(sampleFrameListInput);
        assertTrue(expected.containsAll(sampleFrameListInput) && sampleFrameListInput.containsAll(expected));
    }

    private List<Frame> getExpectedFrameListOutput() {
        return Arrays.asList(
                // Frame 1 Scores
                new Frame(1, Arrays.asList(new Score(10, false)), 20),
                // Frame 2 Scores
                new Frame(2, Arrays.asList(new Score(7, false), new Score(3, false)), 39),
                // Frame 3 Scores
                new Frame(3, Arrays.asList(new Score(9, false), new Score(0, false)), 48),
                // Frame 4 Scores
                new Frame(4, Arrays.asList(new Score(10, false)), 66),
                // Frame 5 Scores
                new Frame(5, Arrays.asList(new Score(0, false), new Score(8, false)), 74),
                // Frame 6 Scores
                new Frame(6, Arrays.asList(new Score(8, false), new Score(2, false)), 84),
                // Frame 7 Scores
                new Frame(7, Arrays.asList(new Score(0, true), new Score(6, false)), 90),
                // Frame 8 Scores
                new Frame(8, Arrays.asList(new Score(10, false)), 120),
                // Frame 9 Scores
                new Frame(9, Arrays.asList(new Score(10, false)), 148),
                // Frame 10 Scores
                new Frame(10, Arrays.asList(new Score(10, false), new Score(8, false), new Score(1, false)), 167));
    }

    private List<Frame> getSampleFrameListInput() {
        return Arrays.asList(
                // Frame 1 Scores
                new Frame(1, Arrays.asList(new Score(10, false)), 0),
                // Frame 2 Scores
                new Frame(2, Arrays.asList(new Score(7, false), new Score(3, false)), 0),
                // Frame 3 Scores
                new Frame(3, Arrays.asList(new Score(9, false), new Score(0, false)), 0),
                // Frame 4 Scores
                new Frame(4, Arrays.asList(new Score(10, false)), 0),
                // Frame 5 Scores
                new Frame(5, Arrays.asList(new Score(0, false), new Score(8, false)), 0),
                // Frame 6 Scores
                new Frame(6, Arrays.asList(new Score(8, false), new Score(2, false)), 0),
                // Frame 7 Scores
                new Frame(7, Arrays.asList(new Score(0, true), new Score(6, false)), 0),
                // Frame 8 Scores
                new Frame(8, Arrays.asList(new Score(10, false)), 0),
                // Frame 9 Scores
                new Frame(9, Arrays.asList(new Score(10, false)), 0),
                // Frame 10 Scores
                new Frame(10, Arrays.asList(new Score(10, false), new Score(8, false), new Score(1, false)), 0));
    }

}