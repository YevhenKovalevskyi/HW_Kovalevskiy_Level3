package ru.gb.hw01.task2;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gb.hw01.task2.services.ArrayService;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@ExtendWith(WatchmanExtension.class)
public class MainTest {
    
    @Test
    public void when_ArrayIsEmpty_Expect_Exception() {
        assertThatThrownBy(() -> {
            log.info("Checking for exception:");
            ArrayService.convertArrayToArrayList(new String[]{});
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Array is empty! Can't handle empty arrays!");
    }
    
    @ParameterizedTest
    @MethodSource("ru.gb.hw01.task2.TestProviders#initConvertArrayToArrayListProvider")
    public <T> void when_ReturnConvertedArrayToArrayList_should_BeEqual(
            T[] checkedArray, ArrayList<T> rightResult, ArrayList<T> wrongResult
    ) {
        log.info("Checking for converted list: equal");

        assertThat(ArrayService.convertArrayToArrayList(checkedArray)).isEqualTo(rightResult);
    }
    
    @ParameterizedTest
    @MethodSource("ru.gb.hw01.task2.TestProviders#initConvertArrayToArrayListProvider")
    public <T> void when_ReturnConvertedArrayToArrayList_should_BeNotEqual(
            T[] checkedArray, ArrayList<T> rightResult, ArrayList<T> wrongResult
    ) {
        log.info("Checking for converted list: not equal");
    
        ArrayList<T> list = ArrayService.convertArrayToArrayList(checkedArray);
    
        assertThat(ArrayService.convertArrayToArrayList(checkedArray)).isNotEqualTo(wrongResult);
    }
}
