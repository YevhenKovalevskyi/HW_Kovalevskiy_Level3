package ru.gb.hw06.task1;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw06.task1.exceptions.EmptyArrayException;
import ru.gb.hw06.task1.exceptions.UnecceptableArraySizeException;
import ru.gb.hw06.task1.exceptions.UnsuitableArrayException;
import ru.gb.hw06.task1.messages.Messages;
import ru.gb.hw06.task1.services.ArrayService;

import java.util.Arrays;

/**
 * The Main Class represents homework #6 #task1
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info(Messages.START_PROGRAM.getLogMessage());
        System.out.println(Messages.START_PROGRAM.getOutMessage());
        
        getArraySlice(new int[]{1, 3, 0, 4, 12, 4, 4, 8, 44, 1}, 4);
        makeArrayCheck(new int[]{1, 3, 0, 4, 12, 4, 8, 44, 1}, new int[]{1, 4});
        
        log.info(Messages.END_PROGRAM.getLogMessage());
        System.out.println(Messages.END_PROGRAM.getOutMessage());
    }
    
    /**
     * The method gets array slice
     *
     * @param initialArray to slice
     * @param keyNumber to slice from
     */
    public static void getArraySlice(int[] initialArray, int keyNumber) {
        try {
            String initialData = Arrays.toString(initialArray);
            log.info(Messages.START_COMPOSITION.getLogMessage(), initialData);
            System.out.printf(Messages.START_COMPOSITION.getOutMessage(), initialData);
        
            int[] slicedArray = ArrayService.getArraySliceByKey(initialArray, keyNumber);
            
            String finalData = Arrays.toString(slicedArray);
            log.info(Messages.FINAL_COMPOSITION.getLogMessage(), finalData);
            System.out.printf(Messages.FINAL_COMPOSITION.getOutMessage(), finalData);
        } catch (EmptyArrayException | UnsuitableArrayException e) {
            String errorMessage = e.getMessage();
            log.error(Messages.MAIN_EXCEPTION.getLogMessage(), errorMessage);
            System.out.printf(Messages.MAIN_EXCEPTION.getOutMessage(), errorMessage);
        }
    }
    
    /**
     * The method makes arrays check
     *
     * @param initialArray to check into
     * @param checkArray to check with
     */
    public static void makeArrayCheck(int[] initialArray, int[] checkArray) {
        try {
            String initialData = Arrays.toString(initialArray);
            log.info(Messages.START_COMPOSITION.getLogMessage(), initialData);
            System.out.printf(Messages.START_COMPOSITION.getOutMessage(), initialData);
        
            boolean valid = ArrayService.checkArraysIntersection(initialArray, checkArray);
            String checkData = Arrays.toString(checkArray);
            Messages resultMessage = valid
                    ? Messages.CHECKED_ARRAY_VALID : Messages.CHECKED_ARRAY_INVALID;
                    
            log.info(resultMessage.getLogMessage(), checkData);
            System.out.printf(resultMessage.getOutMessage(), checkData);
        } catch (EmptyArrayException | UnecceptableArraySizeException e) {
            String errorMessage = e.getMessage();
            log.error(Messages.MAIN_EXCEPTION.getLogMessage(), errorMessage);
            System.out.printf(Messages.MAIN_EXCEPTION.getOutMessage(), errorMessage);
        }
    }
}
