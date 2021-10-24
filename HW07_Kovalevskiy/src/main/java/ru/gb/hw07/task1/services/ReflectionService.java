package ru.gb.hw07.task1.services;

import ru.gb.hw07.task1.annotations.Order;
import ru.gb.hw07.task1.exceptions.AnnotationInvalidException;
import ru.gb.hw07.task1.exceptions.MethodInvokeException;
import ru.gb.hw07.task1.messages.Messages;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectionService {
    
    public static Method getBeforeSuiteAnnotationMethod(
            Map<Method, Annotation[]> classMethodsAnnotations
    ) throws AnnotationInvalidException {
        String neededAnnotation = "BeforeSuite";
        List<Method> methods = new ArrayList<>();
        
        classMethodsAnnotations.forEach((method, annotations) -> {
            Arrays.stream(annotations).forEach(annotation -> {
                if (annotation.annotationType().getSimpleName().equals(neededAnnotation)) {
                    methods.add(method);
                }
            });
        });
        
        if (methods.size() != 1) {
            throw new AnnotationInvalidException(
                    String.format(Messages.ANNOTATION_INVALID.getOutMessage(), neededAnnotation)
            );
        }
        
        return methods.get(0);
    }
    
    public static Method getAfterSuiteAnnotationMethod(
            Map<Method, Annotation[]> classMethodsAnnotations
    ) throws AnnotationInvalidException {
        String neededAnnotation = "AfterSuite";
        List<Method> methods = new ArrayList<>();
        
        classMethodsAnnotations.forEach((method, annotations) -> {
            Arrays.stream(annotations).forEach(annotation -> {
                if (annotation.annotationType().getSimpleName().equals(neededAnnotation)) {
                    methods.add(method);
                }
            });
        });
        
        if (methods.size() != 1) {
            throw new AnnotationInvalidException(
                    String.format(Messages.ANNOTATION_INVALID.getOutMessage(), neededAnnotation)
            );
        }
        
        return methods.get(0);
    }
    
    public static Map<Method, Integer> getTestAnnotationMethods(
            Map<Method, Annotation[]> classMethodsAnnotations
    ) throws AnnotationInvalidException {
        String neededAnnotation = "Test";
        Map<Method, Integer> methods = new HashMap<>();
        
        classMethodsAnnotations.forEach((method, annotations) -> {
            Arrays.stream(annotations).forEach(annotation -> {
                if (annotation.annotationType().getSimpleName().equals(neededAnnotation)) {
                    Order order = method.getAnnotation(Order.class);
                    methods.put(method, getMethodOrder(order));
                }
            });
        });
        
        return methods;
    }
    
    private static int getMethodOrder(Order order) {
        if (order == null) {
            return Order.DEFAULT;
        }
        
        int orderValue = order.value();
        int orderMin = Order.MIN;
        int orderMax = Order.MAX;
        
        return Math.min(Math.max(orderValue, orderMin), orderMax);
    }
    
    public static Map<Method, Annotation[]> getClassMethodsAnnotations(Class<?> classType) {
        Map<Method, Annotation[]> classMethodsAnnotations = new HashMap<>();
        Arrays.stream(classType.getDeclaredMethods()).forEach(method -> {
            classMethodsAnnotations.put(method, method.getDeclaredAnnotations());
        });
        
        return classMethodsAnnotations;
    }
    
    public static String getFullClassPathByStringName(Class<?> currentClass, String className) {
        String currentPackage = currentClass.getPackage().getName();
        String currentTestsDir = "tests";
        
        return String.format("%s.%s.%s", currentPackage, currentTestsDir, className);
    }
    
    public static void runMethod(Object object, Method method) throws MethodInvokeException {
        try {
            method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new MethodInvokeException(
                    String.format(Messages.METHOD_INVOKE_ERROR.getOutMessage(), e.getMessage())
            );
        }
    }
    
    public static void runMethods(Object object, List<Method> methods) throws MethodInvokeException {
        try {
            for (Method method : methods) {
                method.invoke(object);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new MethodInvokeException(
                    String.format(Messages.METHOD_INVOKE_ERROR.getOutMessage(), e.getMessage())
            );
        }
    }
}
