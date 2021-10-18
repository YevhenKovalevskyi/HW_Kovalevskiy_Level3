package ru.gb.hw06.task1;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gb.hw06.task1.exceptions.EmptyArrayException;
import ru.gb.hw06.task1.exceptions.UnecceptableArraySizeException;
import ru.gb.hw06.task1.exceptions.UnsuitableArrayException;
import ru.gb.hw06.task1.messages.Messages;
import ru.gb.hw06.task1.services.ArrayService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@ExtendWith(WatchmanExtension.class)
public class MainTest {
    
    /* -- checks for exceptions ------------------------------------------------------------------------------------- */
    @Test
    public void when_GetArraySliceAndArrayIsEmpty_Expect_Exception() {
        assertThatThrownBy(() -> {
            log.info("Checking for exception:");
            ArrayService.getArraySliceByKey(new int[]{}, 1);
        }).isInstanceOf(EmptyArrayException.class)
                .hasMessage(Messages.EMPTY_ARRAY.getOutMessage());
    }
    
    @Test
    public void when_GetArraySliceAndValueToStartFromIsMissingInArray_Expect_Exception() {
        assertThatThrownBy(() -> {
            log.info("Checking for exception:");
            ArrayService.getArraySliceByKey(new int[]{1, 2, 3, 4, 5}, 8);
        }).isInstanceOf(UnsuitableArrayException.class)
                .hasMessage(Messages.UNSUITABLE_ARRAY.getOutMessage());
    }
    
    @ParameterizedTest
    @MethodSource("ru.gb.hw06.task1.TestProviders#initCheckArraysIntersectionOneEmptyProvider")
    public void when_CheckArraysIntersectionAndOneArrayIsEmpty_Expect_Exception(
            int[] arrayBeingChecked, int[] arrayToCheck
    ) {
        assertThatThrownBy(() -> {
            log.info("Checking for exception:");
            ArrayService.checkArraysIntersection(arrayBeingChecked, arrayToCheck);
        }).isInstanceOf(EmptyArrayException.class)
                .hasMessage(Messages.EMPTY_ARRAY.getOutMessage());
    }
    
    @ParameterizedTest
    @MethodSource("ru.gb.hw06.task1.TestProviders#initCheckArraysIntersectionUnacceptableSizesProvider")
    public void when_CheckArraysIntersectionAndArraysSizesAreUnacceptable_Expect_Exception(
            int[] arrayBeingChecked, int[] arrayToCheck
    ) {
        assertThatThrownBy(() -> {
            log.info("Checking for exception:");
            ArrayService.checkArraysIntersection(arrayBeingChecked, arrayToCheck);
        }).isInstanceOf(UnecceptableArraySizeException.class)
                .hasMessage(Messages.ARRAYS_SIZE_UNACCEPTABLE.getOutMessage());
    }
    /* // checks for exceptions ------------------------------------------------------------------------------------- */
    
    /* -- checks for functionality ---------------------------------------------------------------------------------- */
    @ParameterizedTest
    @MethodSource("ru.gb.hw06.task1.TestProviders#initGetArraySliceEqualProvider")
    @SneakyThrows
    public void when_GetArraySlice_should_BeEqual(
            int[] arrayBeingChecked, int valToSliceFrom, int[] rightResult
    ) {
        log.info("Checking for array slice: equal");
        assertThat(ArrayService.getArraySliceByKey(arrayBeingChecked, valToSliceFrom)).isEqualTo(rightResult);
    }
    
    @ParameterizedTest
    @MethodSource("ru.gb.hw06.task1.TestProviders#initGetArraySliceNotEqualProvider")
    @SneakyThrows
    public void when_GetArraySlice_should_NotBeEqual(
            int[] arrayBeingChecked, int valToSliceFrom, int[] wrongResult
    ) {
        log.info("Checking for array slice: not equal");
        assertThat(ArrayService.getArraySliceByKey(arrayBeingChecked, valToSliceFrom)).isNotEqualTo(wrongResult);
    }
    
    
    @ParameterizedTest
    @MethodSource("ru.gb.hw06.task1.TestProviders#initCheckArraysIntersectionEqualProvider")
    @SneakyThrows
    public void when_CheckArraysIntersection_should_BeEqual(
            int[] arrayBeingChecked, int[] arrayToCheck
    ) {
        log.info("Checking for arrays intersection: true");
        assertThat(ArrayService.checkArraysIntersection(arrayBeingChecked, arrayToCheck)).isTrue();
    }
    
    @ParameterizedTest
    @MethodSource("ru.gb.hw06.task1.TestProviders#initCheckArraysIntersectionNotEqualProvider")
    @SneakyThrows
    public void when_CheckArraysIntersection_should_NotBeEqual(
            int[] arrayBeingChecked, int[] arrayToCheck
    ) {
        log.info("Checking for arrays intersection: false");
        assertThat(ArrayService.checkArraysIntersection(arrayBeingChecked, arrayToCheck)).isFalse();
    }
    /* // checks for functionality ---------------------------------------------------------------------------------- */
}
