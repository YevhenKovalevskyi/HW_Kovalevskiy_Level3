package ru.gb.hw04.task2.server;

import ru.gb.hw04.task2.helpers.FilesHelper;
import ru.gb.hw04.task2.helpers.PrintAndLogHelper;
import ru.gb.hw04.task2.messages.Messages;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * HistoryService Class represents functionality for working history logs
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
public class HistoryService {
    
    private static final int ROWS_TO_DISPLAY = 20;
    private static final String PATH_TO_LOG = "chat/%s.history.log";
    
    /**
     * The method writes a message to the user's log file
     *
     * @param login of the user
     * @param message to user's log
     */
    public static void writeHistoryLog(String login, String message) {
        Path path = getPathToHistoryLog(login);
        
        if (!existsHistoryLog(path) || !isWritableHistoryLog(path)) {
            PrintAndLogHelper.printAndLogError(Messages.LOG_EXISTS_WRITABLE_ERROR);
        }
    
        try {
            Files.write(path, Collections.singletonList(message), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            PrintAndLogHelper.logErrorOnlyWithArgs(Messages.LOG_WRITE_ERROR, e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * The method reads all messages from the user's log file
     *
     * @param login of the user
     * @return list of messages
     */
    public static List<String> readHistoryLog(String login) {
        Path path = getPathToHistoryLog(login);
    
        if (!existsHistoryLog(path) || !isReadableHistoryLog(path)) {
            PrintAndLogHelper.printAndLogError(Messages.LOG_EXISTS_READABLE_ERROR);
        }
    
        List<String> messages = new ArrayList<>();
    
        try {
            messages = Files.readAllLines(path);
        } catch (IOException e) {
            PrintAndLogHelper.logErrorOnlyWithArgs(Messages.LOG_READ_ERROR, e.getMessage());
            e.printStackTrace();
        }
        
        int rows = messages.size();
        messages = (rows > ROWS_TO_DISPLAY) ? messages.subList((rows - ROWS_TO_DISPLAY), rows) : messages;
        
        return messages;
    }
    
    /**
     * The method gets the path to the user's log file
     *
     * @param login of the user
     * @return path to the file
     */
    private static Path getPathToHistoryLog(String login) {
        String filePath = FilesHelper.getResourceRootFilePathGradle(
                HistoryService.class, "HistoryService.class", String.format(PATH_TO_LOG, login.toLowerCase(Locale.ROOT))
        );
    
        return Paths.get(filePath);
    }
    
    /**
     * The method checks if the file exists
     *
     * @param path to the file
     * @return result of checking
     */
    private static boolean existsHistoryLog(Path path) {
        PrintAndLogHelper.logOnly(Messages.LOG_FILE_CHECKING_FOR_EXISTENCE);
        
        if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createFile(path);
                PrintAndLogHelper.logOnly(Messages.LOG_FILE_CREATED_SUCCESSFULLY);
            } catch (FileAlreadyExistsException e) {
                PrintAndLogHelper.printAndLogError(Messages.LOG_FILE_ALREADY_EXISTS_ERROR);
            } catch (IOException e) {
                PrintAndLogHelper.printAndLogErrorWithArgs(Messages.LOG_FILE_CREATE_ERROR, e.getMessage());
            } catch (SecurityException e) {
                PrintAndLogHelper.printAndLogErrorWithArgs(Messages.LOG_FILE_PERMISSION_ERROR, e.getMessage());
            }
        }
        
        return Files.exists(path, LinkOption.NOFOLLOW_LINKS);
    }
    
    /**
     * The method checks if the file is readable
     *
     * @param path to the file
     * @return result of checking
     */
    private static boolean isReadableHistoryLog(Path path) {
        return Files.isReadable(path);
    }
    
    /**
     * The method checks if the file is writable
     *
     * @param path to the file
     * @return result of checking
     */
    private static boolean isWritableHistoryLog(Path path) {
        return Files.isWritable(path);
    }
}
