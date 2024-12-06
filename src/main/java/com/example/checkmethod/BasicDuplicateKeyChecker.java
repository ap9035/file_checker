package com.example.checkmethod;

import java.util.function.Function;

public class BasicDuplicateKeyChecker implements Checker {
    private final String filePath;
    private int keyColumnNumber = 0;
    private String separator = ",";

    public BasicDuplicateKeyChecker(String filePath, int keyColumnNumber, String separator) {
        this.filePath = filePath;
        this.keyColumnNumber = keyColumnNumber;
        this.separator = separator != null ? separator : ",";
    }

    public BasicDuplicateKeyChecker(String filePath, int keyColumnNumber) {
        this.filePath = filePath;
        this.keyColumnNumber = keyColumnNumber;
        this.separator = ",";
    }

    public BasicDuplicateKeyChecker(String filePath) {
        this.filePath = filePath;
        this.keyColumnNumber = 0;
        this.separator = ",";
    }

    @Override
    public boolean check() {
        boolean checkResult = isDuplicateKey(keyColumnNumber, filePath, separator);
        logger.info ("Duplicate key: {}", checkResult);
        return !checkResult;
    }

    @Override
    public String getDescription() {
        return "Checking duplicate key in file: " + filePath;
    }

    public static boolean isDuplicateKey(int keyColumnNumber, String filePath, String separator) {
        Function<String, String> keyExtractor = line -> {
            String[] parts = line.split(separator);
            if (keyColumnNumber >= parts.length) {
                throw new ArrayIndexOutOfBoundsException("Key column index out of bounds for line: " + line);
            }
            return parts[keyColumnNumber];
        };

        return FileKeyCheckerUtil.hasDuplicateKeys(filePath, keyExtractor);
    }
}
