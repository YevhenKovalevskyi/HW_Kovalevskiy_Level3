package ru.gb.hw01.task3.entities;

import lombok.Value;
import ru.gb.hw01.task3.contracts.Fruit;

@Value
public class Orange implements Fruit {
    
    float weight = 1.5f;
}
