package com.jobsity.challenge.service.impl;

import com.jobsity.challenge.domain.Scoreboard;
import com.jobsity.challenge.service.ScoreboardPrinterService;

import org.springframework.stereotype.Service;

/**
 * ScoreboardPrinterServiceImpl
 */
@Service
public class ScoreboardPrinterServiceImpl implements ScoreboardPrinterService {
    @Override
    public void print(Scoreboard scoreboard) {
        System.out.print("wrong");
    }
}