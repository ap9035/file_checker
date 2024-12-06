package com.example.checkmethod;

import java.io.File;

public class BasicFileChecker implements Checker {
    private final String filePath;

    public BasicFileChecker(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean check() {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    @Override
    public String getDescription() {
        return "Checking file: " + filePath;
    }
}
