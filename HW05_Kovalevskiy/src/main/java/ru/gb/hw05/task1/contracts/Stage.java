package ru.gb.hw05.task1.contracts;

import ru.gb.hw05.task1.entities.Car;

public abstract class Stage {
    
    protected int length;
    protected String description;
    
    public abstract void go(Car c);
}
