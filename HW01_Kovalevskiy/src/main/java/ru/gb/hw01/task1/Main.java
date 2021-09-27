package ru.gb.hw01.task1;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw01.task1.helpers.PrintAndLogHelper;
import ru.gb.hw01.task1.messages.Messages;
import ru.gb.hw01.task1.services.ArrayService;

import java.util.Arrays;

/**
 * The Main Class represents homework #1 #task1
 *
 * @author e.kovalevskiy
 * @version 3.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        PrintAndLogHelper.printAndLog(Messages.START_PROGRAM);
    
        String[] arr1 = {"One", "Two", "Three", "Four", "Five"};
        Integer[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8};
        Object[] arr3 = {"a", 2, false, null};
    
        getSwappedArray(arr1, 3, 1);
        getSwappedArray(arr2, 4, 0);
        getSwappedArray(arr3, 1, 3);
    
        PrintAndLogHelper.printAndLog(Messages.END_PROGRAM);
    }
    
    /**
     * The method gets swapped array
     *
     * @param initialArray array template
     * @param a element index
     * @param b element index
     * @param <T> array type
     */
    public static <T> void getSwappedArray(T[] initialArray, int a, int b) {
        try {
            PrintAndLogHelper.printAndLogWithArgs(Messages.START_COMPOSITION, Arrays.toString(initialArray));
            
            T[] swappedArray = ArrayService.swapElements(initialArray, a, b);
            
            PrintAndLogHelper.printAndLogWithArgs(Messages.FINAL_COMPOSITION, Arrays.toString(swappedArray));
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            PrintAndLogHelper.printAndLogErrorWithArgs(Messages.MAIN_EXCEPTION, e.getMessage());
        }
    }
}
