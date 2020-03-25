package com.jobsity.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jobsity.challenge.domain.Player;
import com.jobsity.challenge.domain.Score;
import com.jobsity.challenge.repository.ScoreRepository;
import com.jobsity.challenge.repository.impl.FileScoreRepositoryImpl;
import com.jobsity.challenge.util.InMemoryScoreFileParser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * ScoreRepositoryTests
 */
@SpringBootTest
@Slf4j
public class ScoreRepositoryTests {

    private ScoreRepository scoreRepository;

    @BeforeEach
    public void init() {
        // get new FileScoreRepositoryImpl instance with a InMemoryScoreFileParser
        scoreRepository = new FileScoreRepositoryImpl(new InMemoryScoreFileParser());

    }

    @Test
    @DisplayName("TestFileScoreRepositoryGetScores")
    public void TestFileScoreRepositoryGetScores() {

        List<Score> expectedResult = getExpectedFileScoreRepositoryGetScoresOutput();
        List<Score> actualResult = scoreRepository.getScores();

        log.debug("Expected");
        System.out.println(expectedResult);
        log.debug("Result");
        System.out.println(actualResult);

        assertEquals(expectedResult.size(), actualResult.size());
        assertTrue(expectedResult.containsAll(actualResult) && actualResult.containsAll(expectedResult));

    }

    private List<Score> getExpectedFileScoreRepositoryGetScoresOutput() {
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

}