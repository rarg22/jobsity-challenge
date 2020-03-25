package com.jobsity.challenge;

import com.jobsity.challenge.exception.ScoreRepositoryException;
import com.jobsity.challenge.service.ScoreboardPrinterService;
import com.jobsity.challenge.service.ScoreboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ChallengeApplication implements CommandLineRunner {

    @Autowired
    private ScoreboardService scoreboardService;

    @Autowired
    private ScoreboardPrinterService scoreboardPrinterService;

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("EXECUTING : Ten Pin Bowling Challenge Application");
            scoreboardPrinterService.print(scoreboardService.getScoreboard());
        } catch (ScoreRepositoryException ex) {
            log.error(ex.getClass().getName());
        }

    }
}
