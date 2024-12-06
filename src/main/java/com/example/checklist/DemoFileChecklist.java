package com.example.checklist;

import com.example.checkmethod.*;

import java.util.ArrayList;
import java.util.List;

public class DemoFileChecklist extends AbstractFileChecklist {

    public DemoFileChecklist() {
        super ();
        List<Checker> checkers = new ArrayList<>(List.of (
                new BasicFileChecker ("src/main/resources/file1.txt"),
                new BasicFileChecker ("src/main/resources/file1.flg"),
                new FirstLineContentChecker ("src/main/resources/file2.flg", "file2.txt"),
                new FixWidthDuplicateKeyChecker ("src/main/resources/file2.txt", 3),
                new BasicDuplicateKeyChecker ("src/main/resources/file1.txt", 0, ",")
        ));
        setBatchFileChecker (checkers);
    }
}