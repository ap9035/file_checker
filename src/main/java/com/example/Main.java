package com.example;

import com.example.checklist.DemoFileChecklist;
import com.example.checklist.StandardBatchChecklist;

import static com.example.checkmethod.Checker.logger;

public class Main {
    public static void main(String[] args) {
//        DemoFileChecklist demoFileCheckList = new DemoFileChecklist ();
//        boolean result = demoFileCheckList.performChecks ();
//        logger.info("Final result: {}", result);
        StandardBatchChecklist standardBatchChecklist = new StandardBatchChecklist("twdetail", "src/main/resources/");
        boolean result = standardBatchChecklist.performChecks();
        logger.info("Final result: {}", result);
    }
}
