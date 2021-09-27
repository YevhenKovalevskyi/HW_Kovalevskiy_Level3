package ru.gb.hw01.task2;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.gb.hw01.WatchmanExtension;
import ru.gb.hw01.task2.services.ArrayService;

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
}
