package ru.gb.hw06.task1.services;

import ru.gb.hw06.task1.exceptions.EmptyArrayException;
import ru.gb.hw06.task1.exceptions.UnecceptableArraySizeException;
import ru.gb.hw06.task1.exceptions.UnsuitableArrayException;
import ru.gb.hw06.task1.messages.Messages;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * ArrayService Class represents functionality for working with arrays
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
public class ArrayService {
    
    /**
     * The method gets a slice of an array by key
     *
     * @param arrayToSlice to get slice
     * @param keyValue to slice from
     * @return array slice
     */
    public static int[] getArraySliceByKey(
            int[] arrayToSlice, int keyValue
    ) throws EmptyArrayException, UnsuitableArrayException {
        int arrayLength = arrayToSlice.length;

        if (arrayLength == 0) {
            throw new EmptyArrayException(Messages.EMPTY_ARRAY.getOutMessage());
        }
        
        int lastkeyValue = Arrays.stream(arrayToSlice)
                .boxed()
                .collect(Collectors.toList())
                .lastIndexOf(keyValue);
        
        if (lastkeyValue < 0) {
            throw new UnsuitableArrayException(Messages.UNSUITABLE_ARRAY.getOutMessage());
        }
        
        return Arrays.copyOfRange(arrayToSlice, Math.min(lastkeyValue + 1, arrayLength), arrayLength);
    }
    
    /**
     * The method checks if the array contains another array
     *
     * @param arrayBeingChecked to check into
     * @param arrayToCheck to check with
     * @return contains or not
     */
    public static boolean checkArraysIntersection(
            int[] arrayBeingChecked, int[] arrayToCheck
    ) throws EmptyArrayException, UnecceptableArraySizeException {
        int arrayLength = arrayBeingChecked.length;
        int checkLength = arrayToCheck.length;
        
        if (arrayLength == 0 || checkLength == 0) {
            throw new EmptyArrayException(Messages.EMPTY_ARRAY.getOutMessage());
        }
    
        if (arrayLength < checkLength) {
            throw new UnecceptableArraySizeException(Messages.ARRAYS_SIZE_UNACCEPTABLE.getOutMessage());
        }

        return Arrays.stream(arrayBeingChecked)
                .boxed()
                .collect(Collectors.toList())
                .containsAll(Arrays.stream(arrayToCheck)
                        .boxed()
                        .collect(Collectors.toList())
                );
    }
}
