package com.jobsity.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.jobsity.challenge.exception.BadInputFileException;
import com.jobsity.challenge.repository.ScoreDto;
import com.jobsity.challenge.util.ScoreFileParser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * ScoreFileParserTests
 */
@SpringBootTest
@Slf4j
public class ScoreFileParserTests {

    private List<ScoreDto> expectedTextFileScoreDtoOutput;

    @Autowired
    @Qualifier("text")
    private ScoreFileParser textFileParser;

    @BeforeEach
    public void init() {
        expectedTextFileScoreDtoOutput = getExpectedTextFileScoreDtoOutput();
    }

    @Test
    @DisplayName("TestScoreTextFileParserWithSampleInputFile")
    public void TestScoreTextFileParserWithSampleInputFile() {
        String fileToRead = "src/test/resources/score_input.txt";
        try {
            List<ScoreDto> actualTextFileScoreDtoOutput = textFileParser.parse(fileToRead);
            assertEquals(expectedTextFileScoreDtoOutput, actualTextFileScoreDtoOutput);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    @DisplayName("TestScoreTextFileParserWithSampleBadInputFile")
    public void TestScoreTextFileParserWithSampleBadInputFile() {
        String fileToRead = "src/test/resources/bad_score_input.txt";
        BadInputFileException ex = assertThrows(BadInputFileException.class, () -> {
            textFileParser.parse(fileToRead);
        });

        String expectedMessage = "Found invalid score result:";
        String actualMessage = ex.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    private List<ScoreDto> getExpectedTextFileScoreDtoOutput(){
        return Arrays.asList(
            // Score #1
            new ScoreDto("Jeff", "10"),
            // Score #2
            new ScoreDto("John", "3"),
            // Score #3
            new ScoreDto("John", "7"),
            // Score #4
            new ScoreDto("Jeff", "7"),
            // Score #5
            new ScoreDto("Jeff", "3"),
            // Score #6
            new ScoreDto("John", "6"),
            // Score #7
            new ScoreDto("John", "3"),
            // Score #8
            new ScoreDto("Jeff", "9"),
            // Score #9
            new ScoreDto("Jeff", "0"),
            // Score #10
            new ScoreDto("John", "10"),
            // Score #11
            new ScoreDto("Jeff", "10"),
            // Score #12
            new ScoreDto("John", "8"),
            // Score #13
            new ScoreDto("John", "1"),
            // Score #14
            new ScoreDto("Jeff", "0"),
            // Score #15
            new ScoreDto("Jeff", "8"),
            // Score #16
            new ScoreDto("John", "10"),
            // Score #17
            new ScoreDto("Jeff", "8"),
            // Score #18
            new ScoreDto("Jeff", "2"),
            // Score #19
            new ScoreDto("John", "10"),
            // Score 20
            new ScoreDto("Jeff", "F"),
            // Score #21
            new ScoreDto("Jeff", "6"),
            // Score #22
            new ScoreDto("John", "9"),
            // Score #23
            new ScoreDto("John", "0"),
            // Score #24
            new ScoreDto("Jeff", "10"),
            // Score #25
            new ScoreDto("John", "7"),
            // Score #26
            new ScoreDto("John", "3"),
            // Score #27
            new ScoreDto("Jeff", "10"),
            // Score #28
            new ScoreDto("John", "4"),
            // Score #29
            new ScoreDto("John", "4"),
            // Score #30
            new ScoreDto("Jeff", "10"),
            // Score #31
            new ScoreDto("Jeff", "8"),
            // Score #32
            new ScoreDto("Jeff", "1"),
            // Score #33
            new ScoreDto("John", "10"),
            // Score #34
            new ScoreDto("John", "9"),
            // Score #35
            new ScoreDto("John", "0"));
    }

}