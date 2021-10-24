package ru.gb.hw07.task1.tests;

import ru.gb.hw07.task1.annotations.AfterSuite;
import ru.gb.hw07.task1.annotations.BeforeSuite;
import ru.gb.hw07.task1.annotations.Order;
import ru.gb.hw07.task1.annotations.Test;

public class FourthTest {
    
    @BeforeSuite
    public void beforeSuiteTest2() {
        System.out.println("BeforeSuite2 has passed");
    }
    
    @AfterSuite
    public void afterSuiteTest() {
        System.out.println("AfterSuite has passed");
    }
    
    @AfterSuite
    public void afterSuiteTest2() {
        System.out.println("AfterSuite2 has passed");
    }
    
    @Test
    public void firstOneTest() {
        System.out.println("firstOneTest has passed");
    }
    
    @Test
    @Order(112)
    public void secondOneTest() {
        System.out.println("secondOneTest has passed");
    }
    
    @Test
    @Order(-10)
    public void thirdOneTest() {
        System.out.println("thirdOneTest has passed");
    }
}
