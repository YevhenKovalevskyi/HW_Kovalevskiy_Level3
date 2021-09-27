package ru.gb.hw01.task1.messages;

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
    OUT_OF_BOUNDS("Requested array index is out of bounds!", "Requested array index is out of bounds!"),
    START_COMPOSITION("Array start composition: {}", "Array start composition: %s\n"),
    FINAL_COMPOSITION("Array final composition: {}", "Array final composition: %s\n\n"),
    MAIN_EXCEPTION("!!! ALARM !!! EXCEPTION: {}", "!!! ALARM !!! EXCEPTION: %s\n");
    
    @Getter private String logMessage;
    @Getter private String outMessage;
}
