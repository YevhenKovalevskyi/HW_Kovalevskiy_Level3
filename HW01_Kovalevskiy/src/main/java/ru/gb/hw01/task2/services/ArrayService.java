package ru.gb.hw01.task2.services;

import ru.gb.hw01.task2.messages.Messages;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ArrayService Class represents functionality for working with arrays
 *
 * @author e.kovalevskiy
 * @version 3.0
 */
public class ArrayService {
    
    /**
     * The method converts an array to an ArrayList
     *
     * @param array array template
     * @param <T> array type
     * @return ArrayList, was got from array
     */
    public static <T> ArrayList<T> convertArrayToArrayList(T[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException(Messages.EMPTY_ARRAY.getOutMessage());
        }
        
        return new ArrayList<>(Arrays.asList(array));
    }
}
