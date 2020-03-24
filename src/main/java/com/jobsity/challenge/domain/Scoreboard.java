package com.jobsity.challenge.domain;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * Scoreboard
 */
@Data
public class Scoreboard {

    private Map<Player,Map<Integer,List<Frame>>> scoreboard;
    
}