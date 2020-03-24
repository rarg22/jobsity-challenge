package com.jobsity.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.jobsity.challenge.domain.Frame;
import com.jobsity.challenge.domain.Player;
import com.jobsity.challenge.domain.Score;
import com.jobsity.challenge.domain.Scoreboard;
import com.jobsity.challenge.service.ScoreboardPrinterService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ScoreboardPrinterServiceTests
 */
@SpringBootTest
public class ScoreboardPrinterServiceTests {

    private String test_scoreboard_output;

    private Scoreboard scoreboard;

    @Autowired
    private ScoreboardPrinterService scoreboardPrinterService;

    @BeforeEach
    public void init() throws IOException {
        test_scoreboard_output =
        "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n" + "Jeff\n"
        + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\t\tX\t8\t1\n"
        + "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n" +
        "John\n"
        + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\t\tX\t9\t0\n"
        + "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151";

        // test_scoreboard_output = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n" + "Jeff\n"
        //         + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\n"
        //         + "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n" + "John\n"
        //         + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\n"
        //         + "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151";

        scoreboard = new Scoreboard();

        // Total Score for JEFF
        Player jeff = new Player("Jeff");
        scoreboard.getScoreboard().put(jeff, Arrays.asList(
                // Frame 1 Scores
                new Frame(1, Arrays.asList(new Score(10, false)), 20),
                // Frame 2 Scores
                new Frame(2, Arrays.asList(new Score(7, false), new Score(3, false)), 39),
                // Frame 3 Scores
                new Frame(3, Arrays.asList(new Score(9, false), new Score(0, false)), 48),
                // Frame 4 Scores
                new Frame(4, Arrays.asList(new Score(10, false)), 66),
                // Frame 5 Scores
                new Frame(5, Arrays.asList(new Score(0, false), new Score(8, false)), 74),
                // Frame 6 Scores
                new Frame(6, Arrays.asList(new Score(8, false), new Score(2, false)), 84),
                // Frame 7 Scores
                new Frame(7, Arrays.asList(new Score(0, true), new Score(6, false)), 90),
                // Frame 8 Scores
                new Frame(8, Arrays.asList(new Score(10, false)), 120),
                // Frame 9 Scores
                new Frame(9, Arrays.asList(new Score(10, false)), 148),
                // Frame 10 Scores
                new Frame(10, Arrays.asList(new Score(10, false), new Score(8, false), new Score(1, false)), 167)));

        // Total Score for JOHN
        Player john = new Player("John");
        scoreboard.getScoreboard().put(john, Arrays.asList(
                // Frame 1 Scores
                new Frame(1, Arrays.asList(new Score(3, false), new Score(7, false)), 16),
                // Frame 2 Scores
                new Frame(2, Arrays.asList(new Score(6, false), new Score(3, false)), 25),
                // Frame 3 Scores
                new Frame(3, Arrays.asList(new Score(10, false)), 44),
                // Frame 4 Scores
                new Frame(4, Arrays.asList(new Score(8, false), new Score(1, false)), 53),
                // Frame 5 Scores
                new Frame(5, Arrays.asList(new Score(10, false)), 82),
                // Frame 6 Scores
                new Frame(6, Arrays.asList(new Score(10, false)), 101),
                // Frame 7 Scores
                new Frame(7, Arrays.asList(new Score(9, false), new Score(0, false)), 110),
                // Frame 8 Scores
                new Frame(8, Arrays.asList(new Score(7, false), new Score(3, false)), 124),
                // Frame 9 Scores
                new Frame(9, Arrays.asList(new Score(4, false), new Score(4, false)), 132),
                // Frame 10 Scores
                new Frame(10, Arrays.asList(new Score(10, false), new Score(9, false), new Score(0, false)), 151)));
    }

    @Test
    @DisplayName("TestStandardOutPrintAgainstSampleOutput")
    public void TestStandardOutPrintAgainstSampleOutput() {
        // Keep current System.out with us
        PrintStream oldOut = System.out;
        // Create a ByteArrayOutputStream so that we can get the output
        // from the call to print
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Change System.out to point out to our stream
        System.setOut(new PrintStream(baos));
        scoreboardPrinterService.print(scoreboard);
        // Reset the System.out
        System.setOut(oldOut);
        // Our baos has the content from the print statement
        String output = new String(baos.toByteArray());
        // Add some assertions out output

        System.out.println("Expected");
        System.out.println(test_scoreboard_output);
        System.out.println("Result");
        System.out.println(output);

        assertEquals(test_scoreboard_output, output);
    }

}