package ru.gb.hw07.task1.tests;

import ru.gb.hw07.task1.annotations.AfterSuite;
import ru.gb.hw07.task1.annotations.BeforeSuite;
import ru.gb.hw07.task1.annotations.Order;
import ru.gb.hw07.task1.annotations.Test;

public class FirstTest {
    
    @BeforeSuite
    public void beforeSuiteTest() {
        System.out.println("BeforeSuite has passed");
    }
    
    @AfterSuite
    public void afterSuiteTest() {
        System.out.println("AfterSuite has passed");
    }
    
    @Test
    @Order(1)
    public void firstOneTest() {
        System.out.println("firstOneTest has passed");
    }
    
    @Test
    @Order(3)
    public void secondOneTest() {
        System.out.println("secondOneTest has passed");
    }
    
    @Test
    @Order(2)
    public void thirdOneTest() {
        System.out.println("thirdOneTest has passed");
    }
}
