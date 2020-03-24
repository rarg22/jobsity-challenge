package com.jobsity.challenge.util;

import java.util.List;

import com.jobsity.challenge.repository.ScoreDto;

import org.springframework.stereotype.Component;

/**
 * FileParser
 */

public interface ScoreFileParser {

    public List<ScoreDto> parse(String fileToRead);
}