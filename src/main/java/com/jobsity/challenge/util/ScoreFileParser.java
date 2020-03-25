package com.jobsity.challenge.util;

import java.util.List;

import com.jobsity.challenge.exception.BadInputFileException;
import com.jobsity.challenge.repository.ScoreDto;

/**
 * FileParser
 */

public interface ScoreFileParser {

    public List<ScoreDto> parse(String fileToRead) throws BadInputFileException;
}