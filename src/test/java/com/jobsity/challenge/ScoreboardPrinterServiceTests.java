package com.jobsity.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jobsity.challenge.domain.Scoreboard;
import com.jobsity.challenge.service.ScoreboardPrinterService;

import org.junit.jupiter.api.BeforeEach;
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
        test_scoreboard_output = new String(Files.readString(Paths.get("src/test/resources/sample_output.txt")));
    }

    @Test
    public void TestStandardOutPrintAgainstSampleOutputFile() {
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
        assertEquals(test_scoreboard_output, output);
    }

}