package ru.gb.hw01.task3;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw01.task3.helpers.PrintAndLogHelper;
import ru.gb.hw01.task3.entities.Apple;
import ru.gb.hw01.task3.entities.Box;
import ru.gb.hw01.task3.entities.Orange;
import ru.gb.hw01.task3.messages.Messages;

import java.util.stream.IntStream;

/**
 * The Main Class represents homework #1 #task3
 *
 * @author e.kovalevskiy
 * @version 2.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        PrintAndLogHelper.printAndLog(Messages.START_PROGRAM);
    
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Apple> anotherAppleBox = new Box<>();
        
        IntStream.range(0, 4).forEach(item -> appleBox.add(new Apple()));
        IntStream.range(0, 5).forEach(item -> orangeBox.add(new Orange()));
        IntStream.range(0, 3).forEach(item -> anotherAppleBox.add(new Apple()));
        
        // box items
        PrintAndLogHelper.printAndLogWithArgs(Messages.BOX_EXAMPLE, orangeBox.toString());
        
        // box weight
        PrintAndLogHelper.printAndLogWithArgs(Messages.BOX_WEIGHT, appleBox.getWeight());
        PrintAndLogHelper.printAndLogWithArgs(Messages.BOX_WEIGHT, orangeBox.getWeight());
        
        // box compare
        PrintAndLogHelper.printAndLogWithArgs(Messages.BOX_COMPARE, anotherAppleBox.compare(orangeBox));
    
        // box combine
        PrintAndLogHelper.printAndLogWithArgs(Messages.BOX_COMBINE, anotherAppleBox.combine(appleBox).toString());
        
        PrintAndLogHelper.printAndLog(Messages.END_PROGRAM);
    }
}
