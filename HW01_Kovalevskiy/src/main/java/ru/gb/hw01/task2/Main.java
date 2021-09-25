package ru.gb.hw01.task2;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw01.task2.helpers.PrintAndLogHelper;
import ru.gb.hw01.task2.messages.Messages;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Main Class represents homework #1 #task2
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        PrintAndLogHelper.printAndLog(Messages.START_PROGRAM);
    
        String[] arr1 = {"One", "Two", "Three", "Four", "Five"};
        Integer[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8};
        Object[] arr3 = {"a", 2, false, null};
    
        convertArrayToArrayList(arr1);
        convertArrayToArrayList(arr2);
        convertArrayToArrayList(arr3);
    
        PrintAndLogHelper.printAndLog(Messages.END_PROGRAM);
    }
    
    /**
     * The method converts an array to an ArrayList
     *
     * @param array array template
     * @param <T> array type
     */
    private static <T> void convertArrayToArrayList(T[] array) {
        try {
            if (array.length == 0) {
                throw new IllegalArgumentException(Messages.EMPTY_ARRAY.getLogMessage());
            }
            
            String startType = array.getClass().getSimpleName();
            PrintAndLogHelper.printAndLogWithArgs(Messages.START_TYPE, startType);
    
            ArrayList<T> list = new ArrayList<>(Arrays.asList(array));

            String finalType = list.getClass().getSimpleName();
            PrintAndLogHelper.printAndLogWithArgs(Messages.FINAL_TYPE, finalType);
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            PrintAndLogHelper.printAndLogErrorWithArgs(Messages.MAIN_EXCEPTION, errorMessage);
        }
    }
}
