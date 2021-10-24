package ru.gb.hw07.task1.annotations;

import java.lang.annotation.*;

/**
 * {@code @Order} is an annotation that is used to configure the order in which the annotated element (i.e., method)
 * should be executed relative to other elements of the same category.
 *
 * <p>If {@code @Order} is declared on an element is less than {@link #MIN} order value, the {@link #MIN} order value
 * will be assigned to the element.
 *
 * <p>If {@code @Order} is declared on an element is more than {@link #MAX} order value, the {@link #MAX} order value
 * will be assigned to the element.
 *
 * <p>If {@code @Order} is not explicitly declared on an element, the {@link #DEFAULT} order value will be assigned to the element.
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {
    
    /**
     * Minimum order value, equal to the int 0
     */
    int MIN = 0;
    
    /**
     * Maximum order value, equal to the int 10
     */
    int MAX = 10;
    
    /**
     * Default order value, equal to the int 5
     */
    int DEFAULT = 5;
    
    /**
     * The order value for the annotated element (i.e., method).
     *
     * <p>Elements are ordered based on priority where a lower value has greater priority than a higher value.
     * For example, int 0 has the highest priority.
     */
    int value() default DEFAULT;
}