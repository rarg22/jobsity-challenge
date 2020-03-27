package com.jobsity.challenge.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.jobsity.challenge.exception.BadInputFileException;
import com.jobsity.challenge.repository.ScoreDto;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * ScoreTextFileParser
 */
@Component
@Primary
@Qualifier("text")
@Slf4j
public class ScoreTextFileParser implements ScoreFileParser {

    private static final Pattern SCORE_ROW_PATTERN = Pattern.compile("^(\\w*\\s([\\d]|F|10))$");

    @Override
    public List<ScoreDto> parse(String fileToRead) throws BadInputFileException {

        if (fileToRead == null || fileToRead.length() == 0) {
            throw new RuntimeException("missing file name");
        }
        try {
            log.info(String.format("parsing file %s", fileToRead));
            String content = Files.readString(Paths.get(fileToRead));

            // split by new line char and remove any blank values
            List<String> scoreRows = Arrays.asList(content.split("\n")).stream()
                    .filter((s) -> !s.isBlank() && !s.isEmpty()).map(s -> s.trim()).collect(Collectors.toList());
            validate(scoreRows);
            return buildScoreDtoList(scoreRows);

        } catch (IOException ex) {
            log.error(String.format("failed to parse: %s", fileToRead));
            throw new BadInputFileException(ex);
        }
    }

    private void validate(List<String> scoreRows) throws BadInputFileException {
        // Cycle through all the score rows. Trim and split them by " " char. Valid
        // format should be "Player value"
        for (String scoreRow : scoreRows) {
            Matcher m = SCORE_ROW_PATTERN.matcher(scoreRow);
            if (!m.matches()) {
                throw new BadInputFileException(String.format("Found invalid score result: %s", scoreRow));
            }
        }
    }

    private List<ScoreDto> buildScoreDtoList(List<String> scoreRows) {
        return scoreRows.stream().map((sr) -> {
            String[] rowTokens = sr.split(" ");
            return new ScoreDto(rowTokens[0], rowTokens[1]);
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

}
