package com.jobsity.challenge.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jobsity.challenge.domain.Frame;
import com.jobsity.challenge.domain.Player;
import com.jobsity.challenge.domain.Score;
import com.jobsity.challenge.service.ScoreSortingService;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * ScoreSortingServiceImpl
 */
@Service
@Slf4j
public class ScoreSortingServiceImpl implements ScoreSortingService {

    @Override
    public Map<Player, List<Frame>> arrange(List<Score> scores) {
        Map<Player, List<Frame>> framesByPlayer = new HashMap<>();
        Map<Player, List<Score>> scoresByPlayer = scores.stream().collect(Collectors.groupingBy(Score::getPlayer));
        for (Map.Entry<Player, List<Score>> scoreByPlayer : scoresByPlayer.entrySet()) {
            List<Score> playerScores = scoreByPlayer.getValue();

            List<Frame> playerFrames = new ArrayList<>();
            // Initialize all 10 frames per player
            playerFrames.addAll(getFrames());
            int currentFrame = 1;
            // loop over scores to assign to corresponding frame.
            log.debug("Scores: " + playerScores.toString());
            for (Score playerScore : playerScores) {
                // get current frame and add the current score
                Frame frame = playerFrames.get(currentFrame - 1);
                frame.getScores().add(playerScore);
                if (currentFrame != 10) {
                    // if the score was a strike, increase the frame counter and move to next frame
                    if (frame.getScores().size() == 1 && playerScore.getValue() == 10) {
                        currentFrame++;
                    }
                    // if the current frame has at most 2 scores and its not the last frame, move to
                    // next frame.
                    if (frame.getScores().size() == 2) {
                        currentFrame++;
                    }
                }
            }
            framesByPlayer.put(scoreByPlayer.getKey(), playerFrames);
        }
        return framesByPlayer;
    }

    private List<Frame> getFrames() {
        // every match will contain a total of 10 frames per player
        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            frames.add(new Frame(i, new ArrayList<>()));
        }
        return frames;
    }
}