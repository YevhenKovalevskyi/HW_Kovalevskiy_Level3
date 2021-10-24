package ru.gb.hw07.task1.tests;

import ru.gb.hw07.task1.annotations.AfterSuite;
import ru.gb.hw07.task1.annotations.Test;

public class ThirdTest {
    
    @AfterSuite
    public void afterSuiteTest() {
        System.out.println("AfterSuite has passed");
    }
    
    @Test
    public void firstOneTest() {
        System.out.println("firstOneTest has passed");
    }
    
    @Test
    public void secondOneTest() {
        System.out.println("secondOneTest has passed");
    }
    
    @Test
    public void thirdOneTest() {
        System.out.println("thirdOneTest has passed");
    }
}
