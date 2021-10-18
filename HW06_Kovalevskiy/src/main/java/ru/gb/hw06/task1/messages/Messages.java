package ru.gb.hw06.task1.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Messages Enum represents text messages for use and localization(if it needed)
 */
@AllArgsConstructor
public enum Messages {
    START_PROGRAM("Start program...", "\nStart program...\n"),
    END_PROGRAM("End program.\n", "End program."),
    EMPTY_ARRAY("Array is empty! Can't handle empty arrays!", "Array is empty! Can't handle empty arrays!"),
    ARRAYS_SIZE_UNACCEPTABLE("Array sizes have an invalid ratio!", "Array sizes have an invalid ratio!"),
    UNSUITABLE_ARRAY("Input array does not contain the required value!", "Input array does not contain the required value!"),
    START_COMPOSITION("Array start composition: {}", "Array start composition: %s\n"),
    FINAL_COMPOSITION("Array final composition: {}", "Array final composition: %s\n\n"),
    CHECKED_ARRAY_VALID("Checked array contains values: {}", "Checked array contains values: %s\n\n"),
    CHECKED_ARRAY_INVALID("Checked array doesn't contain values: {}", "Checked array doesn't contain values: %s\n\n"),
    MAIN_EXCEPTION("!!! ALARM !!! EXCEPTION: {}", "!!! ALARM !!! EXCEPTION: %s\n\n");
    
    @Getter private String logMessage;
    @Getter private String outMessage;
}
