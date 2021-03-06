package ru.gb.hw01.task2;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw01.task2.helpers.PrintAndLogHelper;
import ru.gb.hw01.task2.helpers.ReflectionHelper;
import ru.gb.hw01.task2.messages.Messages;
import ru.gb.hw01.task2.services.ArrayService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Main Class represents homework #1 #task2
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
    
        getArrayList(arr1);
        getArrayList(arr2);
        getArrayList(arr3);
    
        PrintAndLogHelper.printAndLog(Messages.END_PROGRAM);
    }
    
    /**
     * The method gets an ArrayList
     *
     * @param array array template
     * @param <T> array type
     */
    public static <T> void getArrayList(T[] array) {
        try {
            PrintAndLogHelper.printAndLogWithArgs(
                    Messages.START_TYPE, ReflectionHelper.getObjectType(array), Arrays.toString(array)
            );
            
            ArrayList<T> list = ArrayService.convertArrayToArrayList(array);
            
            PrintAndLogHelper.printAndLogWithArgs(
                    Messages.FINAL_TYPE,  ReflectionHelper.getObjectType(list), list.toString()
            );
        } catch (IllegalArgumentException e) {
            PrintAndLogHelper.printAndLogErrorWithArgs(Messages.MAIN_EXCEPTION, e.getMessage());
        }
    }
}
