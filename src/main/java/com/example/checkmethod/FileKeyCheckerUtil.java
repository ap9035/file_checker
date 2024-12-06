package com.example.checkmethod;


import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class FileKeyCheckerUtil {

    public static boolean hasDuplicateKeys(String filePath, Function<String, String> keyExtractor) {
        Set<String> keys = new HashSet<>();
        HashMap<String, Integer> keyLine = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                line = line.strip();
                lineNumber++;
                try {
                    String key = keyExtractor.apply(line);
                    if (keys.contains(key)) {
                        System.err.println("Duplicate key found in line: " + lineNumber + " and line: " + keyLine.get(key));
                        System.err.println("Key: " + key);
                        return true;
                    }
                    keys.add(key);
                    keyLine.put(key, lineNumber);
                } catch (Exception e) {
                    System.err.println("Error extracting key from line: " + line + ". Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }

        if (keys.isEmpty()) {
            throw new IllegalArgumentException("No keys found in file: " + filePath);
        }
        return false;
    }
}