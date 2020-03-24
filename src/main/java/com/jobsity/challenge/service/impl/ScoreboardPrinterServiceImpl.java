package com.jobsity.challenge.service.impl;

import java.util.List;
import java.util.Map;

import com.jobsity.challenge.domain.Frame;
import com.jobsity.challenge.domain.Player;
import com.jobsity.challenge.domain.Score;
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
        String scoreboardDisplay = createConsoleScoreboardDisplay(scoreboard);
        System.out.print(scoreboardDisplay);
    }

    private String createConsoleScoreboardDisplay(Scoreboard scoreboard) {
        StringBuilder sb = new StringBuilder();
        sb.append("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        Map<Player, List<Frame>> framesByPlayer = scoreboard.getScoreboard();
        for (Map.Entry<Player, List<Frame>> frameByPlayer : framesByPlayer.entrySet()) {
            Player player = frameByPlayer.getKey();
            sb.append("\n").append(player.getName());
            sb.append("\nPinfalls");
            List<Frame> frames = frameByPlayer.getValue();
            for (Frame frame : frames) {
                String displayStr = "";
                List<Score> scores = frame.getScores();
                // If only 1 score is listed, it means its a Strike(X)
                if (scores.size() == 1) {
                    displayStr = "\t\tX";
                }
                if (scores.size() >= 2) {
                    // check if this frame is a Spare(/) by adding 1st and 2nd score attempts
                    if (scores.get(0).getValue() + scores.get(1).getValue() == 10) {
                        // check for foul shot. If true "F" else the score value
                        String scoreStr1 = (scores.get(0).isFoul()) ? "F" : String.valueOf(scores.get(0).getValue());
                        // If Spare, 2nd score will always be represented by "/"
                        String scoreStr2 = "/";
                        displayStr = String.format("\t%s\t%s", scoreStr1, scoreStr2);
                    } else {
                        // check for foul shot. If true "F" else the score value for each score
                        String scoreStr1 = (scores.get(0).isFoul()) ? "F" : String.valueOf(scores.get(0).getValue());
                        String scoreStr2 = (scores.get(1).isFoul()) ? "F" : String.valueOf(scores.get(1).getValue());
                        displayStr = String.format("\t%s\t%s", scoreStr1, scoreStr2);
                    }
                }
                // Last case only applies to last frame
                if (scores.size() == 3) {
                    // check for foul shot. If true "F" else the score value for each score. If score == 10, then "X" else use score value
                    String scoreStr1 = (scores.get(0).isFoul()) ? "F" : (scores.get(0).getValue() == 10) ? "X" : String.valueOf(scores.get(0).getValue());
                    String scoreStr2 = (scores.get(1).isFoul()) ? "F" : (scores.get(1).getValue() == 10) ? "X" : String.valueOf(scores.get(1).getValue());
                    String scoreStr3 = (scores.get(2).isFoul()) ? "F" : (scores.get(2).getValue() == 10) ? "X" : String.valueOf(scores.get(2).getValue());
                    displayStr = String.format("\t\t%s\t%s\t%s", scoreStr1, scoreStr2, scoreStr3);
                }
                sb.append(displayStr);
            }
            sb.append("\nScore");
            for (Frame frame : frames) {
                sb.append("\t\t").append(frame.getTotalScore());
            }
        }
        return sb.toString();
    }
}