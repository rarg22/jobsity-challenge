package com.jobsity.challenge.util;

import java.util.List;

import com.jobsity.challenge.repository.ScoreDto;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * ScoreTextFileParser
 */
@Component
@Primary
@Qualifier("text")
public class ScoreTextFileParser implements ScoreFileParser {

    @Override
    public List<ScoreDto> parse(String fileToRead) {
        // try {
        // String fileName = fileToRead.substring(0, fileToRead.lastIndexOf("."));
        // String extension = fileToRead.substring(fileToRead.lastIndexOf(".") + 1);

        // log.info(String.format("reading from %s file", fileToRead));

        // // String content = new String(Files.readString(Paths.get(fileToRead)));

        // switch (extension) {
        // case "json":
        // return parseJSON(fileToRead);
        // case "txt":
        // return parseTXT(fileToRead);
        // default:
        // return Collections.emptyList();
        // }
        // } catch (Exception ex) {
        // ex.printStackTrace();
        // return null;
        // }
        return null;
    }

    // private List<ScoreDto> parseJSON(String fileToRead) {
    // System.out.println("reading json file");
    // return null;
    // }

    // private List<ScoreDto> parseTXT(String fileToRead) {
    // System.out.println("reading text file");
    // return null;
    // }

}
