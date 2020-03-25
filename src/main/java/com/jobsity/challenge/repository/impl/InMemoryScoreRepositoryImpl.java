package com.jobsity.challenge.repository.impl;

import java.util.Collections;
import java.util.List;

import com.jobsity.challenge.domain.Score;
import com.jobsity.challenge.repository.ScoreRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * InMemoryScoreRepositoryImpl
 */
@Lazy
@Repository
@Qualifier("inmemory")
@ConditionalOnProperty(name = "ftr", matchIfMissing = true)
@Slf4j
public class InMemoryScoreRepositoryImpl implements ScoreRepository {
    @Override
    public  List<Score> getScores() {
        log.info("reading from memory");
        return Collections.emptyList();
    }

}