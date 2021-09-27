package ru.gb.hw01.task3.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.gb.hw01.task3.contracts.Fruit;

import java.util.ArrayList;

/**
 * The Box class represents the storage of fruits and internal operations with them.
 *
 * @author e.kovalevskiy
 * @version 3.0
 */
@Slf4j
@ToString
@EqualsAndHashCode
public class Box<T extends Fruit> {
    
    private final ArrayList<T> boxList = new ArrayList<>();
    
    /**
     * The method adds a fruit to the box
     *
     * @param item fruit
     */
    public void add(T item) {
        boxList.add(item);
    }
    
    /**
     * The method gets box weight
     *
     * @return weight
     */
    public float getWeight() {
        float weight = 0;
        
        if (!boxList.isEmpty()) {
            weight = boxList.stream()
                    .map(Fruit::getWeight)
                    .reduce(0f, Float::sum);
        }
        
        return weight;
    }
    
    /**
     * The method compares a pair of boxes
     *
     * @param box another entity for compare
     *
     * @return result of comparison
     */
    public boolean compare(Box<? extends Fruit> box) {
        return box.getWeight() == getWeight();
    }
    
    /**
     * The method combines a pair of boxes
     *
     * @param box another entity for unite
     *
     * @return combined box
     */
    public Box<T> combine(Box<T> box) {
        if (!boxList.isEmpty()) {
            boxList.forEach(box::add);
            boxList.clear();
        }
        
        return box;
    }
}
