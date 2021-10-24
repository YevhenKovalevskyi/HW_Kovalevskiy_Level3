package ru.gb.hw07.task1.annotations;

import java.lang.annotation.*;

/**
 * {@code @Test} is used to signal that the annotated method is a
 * <em>test</em> method.
 *
 * <p>{@code @Test} methods must not be {@code private} or {@code static}
 * and must not return a value.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

}