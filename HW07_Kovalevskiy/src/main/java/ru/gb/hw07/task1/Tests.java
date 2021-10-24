package ru.gb.hw07.task1;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw07.task1.exceptions.AnnotationInvalidException;
import ru.gb.hw07.task1.exceptions.AnnotationsNotFoundException;
import ru.gb.hw07.task1.exceptions.MethodInvokeException;
import ru.gb.hw07.task1.messages.Messages;
import ru.gb.hw07.task1.services.CommonService;
import ru.gb.hw07.task1.services.ReflectionService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class Tests {
    
    public static void start(String className) {
        try {
            Class<?> classType = Class.forName(ReflectionService.getFullClassPathByStringName(Tests.class, className));
            
            start(classType);
        } catch (ClassNotFoundException e) {
            String errorMessage = String.format(Messages.CLASS_NOT_FOUND.getOutMessage(), className);
            log.error(Messages.MAIN_EXCEPTION.getLogMessage(), errorMessage);
            System.out.printf(Messages.MAIN_EXCEPTION.getOutMessage(), errorMessage);
        }
    }
    
    public static void start(Class<?> classType) {
        try {
            Map<Method, Annotation[]> classMethodsAnnotations = ReflectionService.getClassMethodsAnnotations(classType);
            
            if (classMethodsAnnotations.size() == 0) {
                throw new AnnotationsNotFoundException(
                        String.format(Messages.ANNOTATIONS_NOT_FOUND.getOutMessage(), classType)
                );
            }
    
            // Get Methods
            Method beforeSuiteMethod = ReflectionService.getBeforeSuiteAnnotationMethod(classMethodsAnnotations);
            Method afterSuiteMethod = ReflectionService.getAfterSuiteAnnotationMethod(classMethodsAnnotations);
            Map<Method, Integer> testMethods = ReflectionService.getTestAnnotationMethods(classMethodsAnnotations);
            List<Method> sortedTestMethods = CommonService.getMethodsSortedByValue(testMethods);
    
            Object object = classType.newInstance();
            
            // Run Methods
            ReflectionService.runMethod(object, beforeSuiteMethod);
            ReflectionService.runMethods(object, sortedTestMethods);
            ReflectionService.runMethod(object, afterSuiteMethod);
        } catch (
                AnnotationsNotFoundException | AnnotationInvalidException | MethodInvokeException |
                        InstantiationException | IllegalAccessException e
        ) {
            String errorMessage = e.getMessage();
            log.error(Messages.MAIN_EXCEPTION.getLogMessage(), errorMessage);
            System.out.printf(Messages.MAIN_EXCEPTION.getOutMessage(), errorMessage);
        }
    }
}
