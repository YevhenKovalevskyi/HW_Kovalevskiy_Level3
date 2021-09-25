package ru.gb.hw01.task2.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Messages Enum represents text messages for use and localization(if it needed)
 */
@AllArgsConstructor
public enum Messages {
    START_PROGRAM("Start program...", "\nStart program...\n"),
    END_PROGRAM("End program.\n", "End program.\n"),
    EMPTY_ARRAY("Array is empty! Can't handle empty arrays!", "Array is empty! Can't handle empty arrays!"),
    START_TYPE("Start type: {}", "Start type: %s\n"),
    FINAL_TYPE("Final type: {}", "Final type: %s\n\n"),
    MAIN_EXCEPTION("!!! ALARM !!! EXCEPTION: {}", "!!! ALARM !!! EXCEPTION: %s\n");
    
    @Getter private String logMessage;
    @Getter private String outMessage;
}
