package com.jobsity.challenge.service;

import java.util.List;
import java.util.Map;

import com.jobsity.challenge.domain.Frame;
import com.jobsity.challenge.domain.Player;
import com.jobsity.challenge.domain.Score;

/**
 * ShotSortingService
 */
public interface ScoreSortingService {

    public Map<Player,List<Frame>> arrange(List<Score> scores);

}