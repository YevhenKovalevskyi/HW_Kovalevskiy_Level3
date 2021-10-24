package ru.gb.hw07.task1.services;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonService {
    
    public static List<Method> getMethodsSortedByValue(Map<Method, Integer> methods) {
        return methods.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
