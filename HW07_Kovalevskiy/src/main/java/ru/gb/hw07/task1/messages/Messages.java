package ru.gb.hw07.task1.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Messages Enum represents text messages for use and localization(if it needed)
 */
@AllArgsConstructor
public enum Messages {
    START_PROGRAM("Start program...", "\nStart program...\n"),
    END_PROGRAM("End program.\n", "\nEnd program."),
    MAIN_EXCEPTION("!!! ALARM !!! EXCEPTION: {}", "!!! ALARM !!! EXCEPTION: %s\n"),
    
    CLASS_NOT_FOUND("Class [ {} ] not found.", "Class [ %s ] not found."),
    ANNOTATIONS_NOT_FOUND("There are no annotations in the class [ {} ]!", "There are no annotations in the class [ %s ]!"),
    ANNOTATION_INVALID("Annotation [ {} ] is not used correctly!", "Annotation [ %s ] is not used correctly!"),
    METHOD_INVOKE_ERROR("An error occurred while running the method: {}", "An error occurred while running the method: %s");
    
    @Getter
    private String logMessage;
    @Getter private String outMessage;
}
