package ru.gb.hw06.task1;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestProviders {
    
    static Stream<Arguments> initCheckArraysIntersectionOneEmptyProvider() {
        return Stream.of(
                Arguments.of(
                        new int[]{3, 2, 7, 2, 9},
                        new int[]{}
                ),
                Arguments.of(
                        new int[]{},
                        new int[]{3, 2, 7, 2, 9}
                )
        );
    }
    
    static Stream<Arguments> initCheckArraysIntersectionUnacceptableSizesProvider() {
        return Stream.of(
                Arguments.of(
                        new int[]{3, 2, 7},
                        new int[]{3, 2, 7, 4}
                ),
                Arguments.of(
                        new int[]{3},
                        new int[]{3, 2}
                )
        );
    }
    
    static Stream<Arguments> initGetArraySliceEqualProvider() {
        return Stream.of(
                Arguments.of(
                        new int[]{3, 2, 7, 4, 8 ,12, 4, 7},
                        4,
                        new int[]{7}
                ),
                Arguments.of(
                        new int[]{1, 2, 4, 5, 6, 1, 0},
                        4,
                        new int[]{5, 6, 1, 0}
                )
        );
    }
    
    static Stream<Arguments> initGetArraySliceNotEqualProvider() {
        return Stream.of(
                Arguments.of(
                        new int[]{3, 2, 7, 4, 8 ,12, 4, 7},
                        4,
                        new int[]{4, 7}
                ),
                Arguments.of(
                        new int[]{1, 2, 4, 5, 6, 1, 0},
                        4,
                        new int[]{4, 5, 5, 1}
                )
        );
    }
    
    static Stream<Arguments> initCheckArraysIntersectionEqualProvider() {
        return Stream.of(
                Arguments.of(
                        new int[]{3, 2, 7, 4, 8 ,12, 4, 7},
                        new int[]{4, 7}
                ),
                Arguments.of(
                        new int[]{1, 2, 4, 5, 6, 1, 0},
                        new int[]{4, 5, 0}
                )
        );
    }
    
    static Stream<Arguments> initCheckArraysIntersectionNotEqualProvider() {
        return Stream.of(
                Arguments.of(
                        new int[]{3, 2, 7, 4, 8 ,12, 4, 7},
                        new int[]{1, 8}
                ),
                Arguments.of(
                        new int[]{1, 2, 4, 5, 6, 1, 0},
                        new int[]{0, 5, 6, 7}
                )
        );
    }
}
