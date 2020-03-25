package com.jobsity.challenge.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jobsity.challenge.domain.Player;
import com.jobsity.challenge.domain.Score;
import com.jobsity.challenge.exception.BadInputFileException;
import com.jobsity.challenge.exception.ScoreRepositoryException;
import com.jobsity.challenge.repository.ScoreDto;
import com.jobsity.challenge.repository.ScoreRepository;
import com.jobsity.challenge.util.ScoreFileParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * FileScoreRepositoryImpl
 */

@Lazy
@Primary
@Repository
@Qualifier("file")
@Slf4j
public class FileScoreRepositoryImpl implements ScoreRepository {

    @Value("${ftr: }")
    private String fileToRead;

    private ScoreFileParser scoreFileParser;

    @Autowired
    public FileScoreRepositoryImpl(@Qualifier("text") ScoreFileParser scoreFileParser) {
        this.scoreFileParser = scoreFileParser;
    }

    @Override
    public List<Score> getScores() {
        try {
            List<ScoreDto> scoreDtos = scoreFileParser.parse(fileToRead);
            return mapScores(scoreDtos);
        } catch (BadInputFileException e) {
            throw new ScoreRepositoryException(e.getCause());
        }
    }

    private List<Score> mapScores(List<ScoreDto> scoreDtos) {
        Map<String, List<ScoreDto>> scoresByPlayer = scoreDtos.stream()
                .collect(Collectors.groupingBy(ScoreDto::getPlayer));
        List<Score> scores = new ArrayList<>();
        for (Map.Entry<String, List<ScoreDto>> entry : scoresByPlayer.entrySet()) {
            entry.getValue().forEach((sdtos) -> {
                boolean isFoul = (sdtos.getValue().equals("F"));
                scores.add(new Score(
                        // map to Player
                        new Player(entry.getKey()),
                        // parse int, 0 if is foul
                        (isFoul) ? 0 : Integer.parseInt(sdtos.getValue()),
                        // set foul flag
                        isFoul));
            });
        }
        return scores;
    }

}