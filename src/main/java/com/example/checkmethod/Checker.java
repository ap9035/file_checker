package com.example.checkmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Checker {
    Logger logger = LoggerFactory.getLogger(Checker.class);

    String getDescription();
    boolean check();

    default boolean performCheck() {
        logger.info("Performing check: {}", getDescription ());
        boolean result = check();
        logger.info("Check result: {}", result? "Passed" : "Failed");
        return result;
    }
}
