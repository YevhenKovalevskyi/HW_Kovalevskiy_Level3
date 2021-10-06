package ru.gb.hw03.task1.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Messages Enum represents text messages for use and localization(if it needed)
 */
@AllArgsConstructor
public enum Messages {
    START_PROGRAM("Start program...", "\nStart program...\n"),
    END_PROGRAM("End program.\n", "End program.\n"),
    
    LOG_EXISTS_WRITABLE_ERROR(
            "History log file doesn't exists or not writable!", "History log file doesn't exists or not writable!"
    ),
    LOG_EXISTS_READABLE_ERROR(
            "History log file doesn't exists or not readable!", "History log file doesn't exists or not readable!"
    ),
    LOG_WRITE_ERROR(
            "Writing to file failed! {}", "Writing to file failed! %s\n"
    ),
    LOG_READ_ERROR(
            "Reading from file failed! {}", "Reading from file failed! %s\n"
    ),
    LOG_FILE_CHECKING_FOR_EXISTENCE(
            "Checking for the existence of the file.", "Checking for the existence of the file."
    ),
    LOG_FILE_CREATED_SUCCESSFULLY(
            "File created successfully!", "File created successfully!"
    ),
    LOG_FILE_ALREADY_EXISTS_ERROR(
            "File already exists!", "File already exists!"
    ),
    LOG_FILE_CREATE_ERROR(
            "An I/O error occurred: {}", "An I/O error occurred: %s\n"
    ),
    LOG_FILE_PERMISSION_ERROR(
            "No permission to create file: {}", "No permission to create file: %s\n"
    );
    
    @Getter
    private String logMessage;
    @Getter private String outMessage;
}
