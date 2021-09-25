package ru.gb.hw01.task3.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Messages Enum represents text messages for use and localization(if it needed)
 */
@AllArgsConstructor
public enum Messages {
    START_PROGRAM("Start program...", "\nStart program...\n"),
    END_PROGRAM("End program.\n", "End program.\n"),
    BOX_EXAMPLE("Box example: {}", "Box example: %s\n\n"),
    BOX_WEIGHT("Box weight: {}", "Box weight: %.1f\n\n"),
    BOX_COMPARE("Are the boxes the same weight?: {}", "Are the boxes the same weight?: %s\n\n"),
    BOX_COMBINE("Combined box: {}", "Combined box: %s\n\n");
    
    @Getter private String logMessage;
    @Getter private String outMessage;
}
