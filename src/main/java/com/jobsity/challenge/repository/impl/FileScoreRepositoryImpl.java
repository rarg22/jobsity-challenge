package com.jobsity.challenge.repository.impl;

import java.util.List;

import com.jobsity.challenge.domain.Score;
import com.jobsity.challenge.repository.ScoreRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * FileScoreRepositoryImpl
 */

@Lazy
@Primary
@Repository
@Qualifier("file")
@ConditionalOnProperty(name = "ftr")
public class FileScoreRepositoryImpl implements ScoreRepository {

    @Value("${ftr: }")
    private String fileToRead;

    // @Autowired
    // private ScoreFileParser fileParser;

    @Override
    public List<Score> getScores() {
        // try {

        //     List<ScoreDto> scores = fileParser.parse(fileToRead);

        // } catch (Exception ex) {
        //     ex.printStackTrace();
        // }

        return null;
    }

}