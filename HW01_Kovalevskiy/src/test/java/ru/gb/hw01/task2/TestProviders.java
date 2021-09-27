package ru.gb.hw01.task2;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TestProviders {
    
    static Stream<Arguments> initConvertArrayToArrayListProvider() {
        return Stream.of(
                Arguments.of(
                        new Integer[]{3, 2, 7, 2},
                        new ArrayList<Integer>() {{ add(3); add(2); add(7); add(2); }},
                        new ArrayList<Integer>()
                ),
                Arguments.of(
                        new String[]{"3", "2", "7", "2"},
                        new ArrayList<String>() {{ add("3"); add("2"); add("7"); add("2"); }},
                        new ArrayList<String>()
                ),
                Arguments.of(
                        new Object[]{"a", 2, false, null},
                        new ArrayList<Object>() {{ add("a"); add(2); add(false); add(null); }},
                        new ArrayList<>()
                )
        );
    }
}
