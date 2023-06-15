package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Testing class for pure testing purposes
 * tests the logging. loggingtest needs to be in dhe Desktop source package, because otherwise the logback.xml could not be loaded.
 * logging works as intended now.
 */
public class LoggingTest {
    /**
     * logger for logging puposes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingTest.class);

    /**
     * start class for testing the logging function
     * @param args not needed
     */
    public static void main(String[] args) {
        // simple testcase
        try {
            System.out.println(9/0);
        }catch (ArithmeticException exception){
            LOGGER.error("ERROR",exception);
            }
        }
    }

