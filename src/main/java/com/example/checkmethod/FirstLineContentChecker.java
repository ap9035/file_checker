package com.example.checkmethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FirstLineContentChecker implements Checker {
    private final String filePath;
    private final String content;

    public FirstLineContentChecker(String filePath, String content) {
        this.filePath = filePath;
        this.content = content;
    }

    @Override
    public String getDescription() {
        return "Checking first line contain specific string \"" + content + "\" in file: " + filePath;
    }

    @Override
    public boolean check() {
        String firstLine = readFirstLine(filePath);
        if (firstLine != null) {
            return firstLine.contains(content);
        }
        return false;
    }

    /*
        * Read the first line of the file
     */
    private String readFirstLine(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader (filePath))) {
            return reader.readLine ();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean performCheck() {
        return Checker.super.performCheck ();
    }
}
