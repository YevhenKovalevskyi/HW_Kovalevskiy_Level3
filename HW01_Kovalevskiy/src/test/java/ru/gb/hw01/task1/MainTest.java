package ru.gb.hw01.task1;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.gb.hw01.WatchmanExtension;
import ru.gb.hw01.task1.services.ArrayService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@ExtendWith(WatchmanExtension.class)
public class MainTest {
    
    @Test
    public void when_ArrayIsEmpty_Expect_Exception() {
        assertThatThrownBy(() -> {
            log.info("Checking for exception:");
            ArrayService.swapElements(new String[]{}, 1, 2);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Array is empty! Can't handle empty arrays!");
    }
    
    @Test
    public void when_ArrayIndexOutOfBounds_Expect_Exception() {
        assertThatThrownBy(() -> {
            log.info("Checking for exception:");
            ArrayService.swapElements(new Integer[]{1, 2, 3}, 3, 4);
        }).isInstanceOf(ArrayIndexOutOfBoundsException.class)
                .hasMessage("Requested array index is out of bounds!");
    }
}
