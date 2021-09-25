package ru.gb.hw01.task1;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw01.task1.helpers.PrintAndLogHelper;
import ru.gb.hw01.task1.messages.Messages;

import java.util.Arrays;
import java.util.Collections;

/**
 * The Main Class represents homework #1 #task1
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
    
        swapArrayElements(arr1, 3, 1);
        swapArrayElements(arr2, 4, 0);
        swapArrayElements(arr3, 1, 3);
    
        PrintAndLogHelper.printAndLog(Messages.END_PROGRAM);
    }
    
    /**
     * The method swaps two elements of an array
     *
     * @param array array template
     * @param a element index
     * @param b element index
     * @param <T> array type
     */
    private static <T> void swapArrayElements(T[] array, int a, int b) {
        try {
            if (array.length == 0) {
                throw new IllegalArgumentException(Messages.EMPTY_ARRAY.getLogMessage());
            }
            
            String startComposition = Arrays.toString(array);
            PrintAndLogHelper.printAndLogWithArgs(Messages.START_COMPOSITION, startComposition);
    
            Collections.swap(Arrays.asList(array), a, b);
    
            String finalComposition = Arrays.toString(array);
            PrintAndLogHelper.printAndLogWithArgs(Messages.FINAL_COMPOSITION, finalComposition);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            PrintAndLogHelper.printAndLogErrorWithArgs(Messages.MAIN_EXCEPTION, errorMessage);
        }
    }
}
