package com.jobsity.challenge;

import com.jobsity.challenge.repository.ScoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ChallengeApplication implements CommandLineRunner {

    @Autowired
    private ScoreRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("EXECUTING : command line runner");

        // File file = ResourceUtils.getFile("/c/Users/Raul Argueta/Sandbox/demos/challenge/sample.txt");
        
        // String content = new String(Files.readString(Paths.get("/c/Users/Raul Argueta/Sandbox/demos/challenge/sample.txt")));
        // System.out.println(content);

        repository.getScores();

        for (int i = 0; i < args.length; ++i) {
            log.info("args[{}]: {}", i, args[i]);
        }
    }
}
