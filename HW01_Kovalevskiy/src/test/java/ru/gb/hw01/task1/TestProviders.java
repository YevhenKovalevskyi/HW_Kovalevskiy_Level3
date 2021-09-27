package ru.gb.hw01.task1;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestProviders {
    
    static Stream<Arguments> initSwapElementsProvider() {
        return Stream.of(
                Arguments.of(
                        new Integer[]{3, 2, 7, 2, 9},
                        1, 2,
                        new Integer[]{3, 7, 2, 2, 9},
                        new Integer[]{3, 2, 7, 2, 9}
                ),
                Arguments.of(
                        new String[]{"One", "Two", "Three", "Four", "Five"},
                        0, 4,
                        new String[]{"Five", "Two", "Three", "Four", "One"},
                        new String[]{"One", "Two", "Three", "Four", "Five"}
                ),
                Arguments.of(
                        new Object[]{"a", 2, false, null},
                        1, 3,
                        new Object[]{"a", null, false, 2},
                        new Object[]{"a", 2, false, null}
                )
        );
    }
}
