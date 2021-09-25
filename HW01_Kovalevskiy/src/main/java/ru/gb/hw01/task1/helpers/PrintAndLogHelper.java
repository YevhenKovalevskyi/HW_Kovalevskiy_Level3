package ru.gb.hw01.task1.helpers;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw01.task1.messages.Messages;

/**
 * The PrintAndLogHelper Class is a helper class for printing and logging text messages
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class PrintAndLogHelper {
    
    /**
     * The method prints and logs text message
     *
     * @param message text
     */
    public static void printAndLog(Messages message) {
        System.out.println(message.getOutMessage());
        log.info(message.getLogMessage());
    }
    
    /**
     * The method prints and logs text message with arguments
     *
     * @param message text
     * @param args params for substitution
     */
    public static void printAndLogWithArgs(Messages message, Object... args) {
        System.out.printf(message.getOutMessage(), args);
        log.info(message.getLogMessage(), args);
    }
    
    /**
     * The method prints and logs error text message
     *
     * @param message text
     */
    public static void printAndLogError(Messages message, String logMessage) {
        System.out.println(message.getOutMessage());
        log.error(logMessage);
    }
    
    /**
     * The method prints and logs error text message with arguments
     *
     * @param message text
     * @param args params for substitution
     */
    public static void printAndLogErrorWithArgs(Messages message, String logMessage, Object... args) {
        System.out.printf(message.getOutMessage(), args);
        log.error(message.getLogMessage(), args);
    }
    
    /**
     * The method prints text message
     *
     * @param message text
     */
    public static void printOnly(Messages message) {
        System.out.println(message.getOutMessage());
    }
    
    /**
     * The method prints text message with arguments
     *
     * @param message text
     * @param args params for substitution
     */
    public static void printOnlyWithArgs(Messages message, Object... args) {
        System.out.printf(message.getOutMessage(), args);
    }
    
    /**
     * The method logs text message
     *
     * @param message text
     */
    public static void logOnly(Messages message) {
        log.info(message.getLogMessage());
    }
    
    /**
     * The method logs text message with arguments
     *
     * @param message text
     * @param args params for substitution
     */
    public static void logOnlyWithArgs(Messages message, Object... args) {
        log.info(message.getLogMessage(), args);
    }
    
    /**
     * The method logs error text message
     *
     * @param message text
     */
    public static void logErrorOnly(Messages message) {
        log.error(message.getLogMessage());
    }
    
    /**
     * The method logs error text message with arguments
     *
     * @param message text
     * @param args params for substitution
     */
    public static void logErrorOnlyWithArgs(Messages message, Object... args) {
        log.error(message.getLogMessage(), args);
    }
}
