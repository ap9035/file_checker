package com.example.checklist;

import com.example.checkmethod.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractFileChecklist {
    private List<Checker> checkers;
    Logger logger = LoggerFactory.getLogger(Checker.class);

    public void setBatchFileChecker(List<Checker> checkers) {
        this.checkers = checkers;
    }

    public boolean performChecks() {
        boolean result;
        for (Checker checker : checkers) {
            result = checker.performCheck ();
            if (!result) {
                logger.info("Check failed: {}", checker.getDescription ());
                logger.info("Stopping further checks");
                return false;
            }
        }
        return true;
    }
}
