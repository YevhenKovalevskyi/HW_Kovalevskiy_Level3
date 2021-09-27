package ru.gb.hw01.task2.helpers;

/**
 * The ReflectionHelper Class is a helper class for working with Reflection API
 *
 * @author e.kovalevskiy
 * @version 3.0
 */
public class ReflectionHelper {
    
    /**
     * The method gets the type of the object
     *
     * @param object object
     */
    public static String getObjectType(Object object) {
        return object.getClass().getSimpleName();
    }
}
