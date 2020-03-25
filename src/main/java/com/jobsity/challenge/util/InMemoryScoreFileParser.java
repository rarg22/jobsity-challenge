package com.jobsity.challenge.util;

import java.util.Arrays;
import java.util.List;

import com.jobsity.challenge.exception.BadInputFileException;
import com.jobsity.challenge.repository.ScoreDto;

/**
 * InMemoryScoreFileParser
 */
public class InMemoryScoreFileParser implements ScoreFileParser {

    @Override
    public List<ScoreDto> parse(String fileToRead) throws BadInputFileException {
        return Arrays.asList(
                // Score #1
                new ScoreDto("Jeff", "10"),
                // Score #2
                new ScoreDto("John", "3"),
                // Score #3
                new ScoreDto("John", "7"),
                // Score #4
                new ScoreDto("Jeff", "F"),
                // Score #5
                new ScoreDto("Jeff", "3"));
    }

}