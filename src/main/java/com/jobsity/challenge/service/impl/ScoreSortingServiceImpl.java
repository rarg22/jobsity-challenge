package com.jobsity.challenge.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jobsity.challenge.domain.Frame;
import com.jobsity.challenge.domain.Player;
import com.jobsity.challenge.domain.Score;
import com.jobsity.challenge.exception.BadInputFileException;
import com.jobsity.challenge.exception.ScoreSortingServiceException;
import com.jobsity.challenge.service.ScoreSortingService;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * ScoreSortingServiceImpl
 */
@Service
@Primary
public class ScoreSortingServiceImpl implements ScoreSortingService {

    @Override
    public Map<Player, List<Frame>> arrange(List<Score> scores) {
        validateInputScores(scores);

        Map<Player, List<Frame>> framesByPlayer = new HashMap<>();
        Map<Player, List<Score>> scoresByPlayer = scores.stream().collect(Collectors.groupingBy(Score::getPlayer));

        for (Map.Entry<Player, List<Score>> scoreByPlayer : scoresByPlayer.entrySet()) {
            Player player = scoreByPlayer.getKey();
            // Initialize all 10 frames per player
            List<Frame> playerFrames = new ArrayList<>();
            playerFrames.addAll(getFrames());

            int currentFrame = 1;
            List<Score> playerScores = scoreByPlayer.getValue();
            // loop over scores to assign to corresponding frame.
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
            validateFrameScores(player, playerFrames);
            framesByPlayer.put(scoreByPlayer.getKey(), playerFrames);
        }
        return framesByPlayer;
    }

    private void validateFrameScores(Player player, List<Frame> frames) throws ScoreSortingServiceException {
        for (Frame frame : frames) {
            int sum = frame.getScores().stream().collect(Collectors.summingInt(Score::getValue));
            // If not the last frame, validate a maximum of 10 score per frame from all 2
            // shots/scores
            if (frame.getId() != 10 && sum > 10) {
                throw new ScoreSortingServiceException(
                        String.format("Player %s Frame %s total Score exceeds the maximum allowed. Value found: %s ",
                                player.getName(), frame.getId(), sum));
                // if last frame, validate a maximum of 30 score per frame from all 3
                // shots/scores
            } else if (sum > 30) {
                throw new ScoreSortingServiceException(
                        String.format("Player %s Frame %s total Score exceeds the maximum allowed. Value found: %s",
                                player.getName(), frame.getId(), sum));
            }
        }
    }

    private void validateInputScores(List<Score> scores) throws ScoreSortingServiceException {
        for (Score score : scores) {
            if(score.getValue() < 0){
                throw new ScoreSortingServiceException(String.format("Negative value found for player %s. Value: %s", score.getPlayer().getName(), score.getValue()));
            }
        }
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