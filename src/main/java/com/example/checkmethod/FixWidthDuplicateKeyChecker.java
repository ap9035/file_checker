package com.example.checkmethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/*
    * This class is used to check if there is any duplicate key in a file.
    * Only check the first column of the file with a fixed width.
 */
public class FixWidthDuplicateKeyChecker implements Checker {
    private final String filePath;
    private final int keyColumnWidth;

    public FixWidthDuplicateKeyChecker(String filePath, int keyColumnWidth) {
        this.filePath = filePath;
        this.keyColumnWidth = keyColumnWidth;
    }

    @Override
    public boolean check() {
        boolean checkResult = isDuplicateKey(filePath, keyColumnWidth);
        logger.info ("Duplicate key: {}", checkResult);
        return !checkResult;
    }

    @Override
    public String getDescription() {
        return "Checking fixed width duplicate key in file: " + filePath;
    }

    public static boolean isDuplicateKey(String filePath, int keyColumnWidth) {
        Function<String, String> keyExtractor = line -> {
            return line.substring (0, keyColumnWidth);
        };

        return FileKeyCheckerUtil.hasDuplicateKeys(filePath, keyExtractor);
    }
}
