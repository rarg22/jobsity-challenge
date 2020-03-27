package com.jobsity.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jobsity.challenge.domain.Frame;
import com.jobsity.challenge.domain.Player;
import com.jobsity.challenge.domain.Score;
import com.jobsity.challenge.exception.ScoreSortingServiceException;
import com.jobsity.challenge.service.ScoreSortingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * ScoreSortingServiceTests
 */
@SpringBootTest
@Slf4j
public class ScoreSortingServiceTests {

    @Autowired
    private ScoreSortingService scoreSortingService;

    @BeforeEach
    public void init() {

    }

    @Test
    @DisplayName("TestScoreSortingServiceArrangeWithSampleInput")
    public void TestScoreSortingServiceArrangeWithSampleInput() {

        Map<Player, List<Frame>> expected = getExpectedScoreSortingServiceArrangeOutput();
        Map<Player, List<Frame>> actual = scoreSortingService.arrange(getTestScoreListInput());

        log.debug("Expected");
        System.out.println(expected);
        log.debug("Result");
        System.out.println(actual);

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("TestScoreSortingServiceArrangeWithSampleInputWhenFrameTotalScoreExceeds10")
    public void TestScoreSortingServiceArrangeWithSampleInputWhenFrameTotalScoreExceeds10() {
        List<Score> scoreListWithExceedingFrameLimitScore = getTestScoreListInputWithExceedingFrameScore();
        assertThrows(ScoreSortingServiceException.class, ()->scoreSortingService.arrange(scoreListWithExceedingFrameLimitScore));
    }

    private Map<Player, List<Frame>> getExpectedScoreSortingServiceArrangeOutput() {
        Map<Player, List<Frame>> framesByPlayer = new HashMap<>();

        Player jeff = new Player("Jeff");
        Player john = new Player("John");

        // initializes both player's total frames
        framesByPlayer.put(jeff, getFrames());
        framesByPlayer.put(john, getFrames());

        Frame jeffFrame1 = framesByPlayer.get(jeff).get(0);
        jeffFrame1.setId(1);
        jeffFrame1.setScores(Arrays.asList(
                // Score1
                new Score(jeff, 10, false)));

        Frame jeffFrame2 = framesByPlayer.get(jeff).get(1);
        jeffFrame2.setId(2);
        jeffFrame2.setScores(Arrays.asList(
                // Score1
                new Score(jeff, 0, true),
                // Score2
                new Score(jeff, 3, false)));

        Frame johnFrame1 = framesByPlayer.get(john).get(0);
        johnFrame1.setId(1);
        johnFrame1.setScores(Arrays.asList(
                // Score1
                new Score(john, 3, false),
                // Score2
                new Score(john, 7, false)));

        return framesByPlayer;
    }

    private List<Score> getTestScoreListInput() {
        List<Score> scores = new ArrayList<>();

        Player jeff = new Player("Jeff");
        Player john = new Player("John");

        scores.add(new Score(jeff, 10, false));

        scores.add(new Score(john, 3, false));
        scores.add(new Score(john, 7, false));

        scores.add(new Score(jeff, 0, true));
        scores.add(new Score(jeff, 3, false));

        return scores;

    }

    private List<Score> getTestScoreListInputWithExceedingFrameScore() {
        List<Score> scores = new ArrayList<>();

        Player jeff = new Player("Jeff");
        Player john = new Player("John");

        scores.add(new Score(jeff, 10, false));

        scores.add(new Score(john, 3, false));
        scores.add(new Score(john, 8, false));

        scores.add(new Score(jeff, 0, true));
        scores.add(new Score(jeff, 3, false));

        return scores;

    }

    private List<Frame> getFrames() {
        // every match will contain a total of 10 frames per player
        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            frames.add(new Frame(i, new ArrayList<>()));
        }
        return frames;
    }
}