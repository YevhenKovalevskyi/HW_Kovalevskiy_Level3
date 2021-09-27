package ru.gb.hw01.task1.services;

import ru.gb.hw01.task1.messages.Messages;

import java.util.Arrays;
import java.util.Collections;

/**
 * ArrayService Class represents functionality for working with arrays
 *
 * @author e.kovalevskiy
 * @version 3.0
 */
public class ArrayService {
    
    /**
     * The method swaps two elements of an array
     *
     * @param array array
     * @param a array index
     * @param b array index
     * @param <T> array type
     * @return swapped array
     */
    public static <T> T[] swapElements(T[] array, int a, int b) {
        if (array.length == 0) {
            throw new IllegalArgumentException(Messages.EMPTY_ARRAY.getOutMessage());
        }
    
        if (!inBounds(array, a) || !inBounds(array, b)) {
            throw new ArrayIndexOutOfBoundsException(Messages.OUT_OF_BOUNDS.getOutMessage());
        }
    
        Collections.swap(Arrays.asList(array), a, b);
        
        return array;
    }
    
    /**
     * The method checks if array index is within the bounds
     *
     * @param array array
     * @param index array index
     * @param <T> array type
     * @return is within the bounds
     */
    public static <T> boolean inBounds(T[] array, int index) {
        return (index >= 0) && (index < array.length);
    }
}
