package ru.gb.hw05.task1;

import lombok.Value;
import ru.gb.hw05.task1.contracts.Stage;

import java.util.ArrayList;
import java.util.Arrays;

@Value
public class Race {
    
    ArrayList<Stage> stages;
    
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
