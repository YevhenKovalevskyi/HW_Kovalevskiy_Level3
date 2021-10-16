package ru.gb.hw05.task1.entities;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw05.task1.contracts.Stage;
import ru.gb.hw05.task1.messages.Messages;

@Slf4j
public class Road extends Stage {
    
    public Road(int length) {
        this.length = length;
        this.description = String.format("Дорога %d метров", length);
    }
    
    @Override
    public void go(Car c) {
        String carName = c.getName();
        int carSpeed = c.getSpeed();
        
        try {
            log.info(Messages.PARTICIPANT_HAS_STARTED_STAGE.getLogMessage(), carName, description);
            System.out.printf(Messages.PARTICIPANT_HAS_STARTED_STAGE.getOutMessage(), carName, description);
            
            Thread.sleep(length / carSpeed * 1000L);
    
            log.info(Messages.PARTICIPANT_HAS_FINISHED_STAGE.getLogMessage(), carName, description);
            System.out.printf(Messages.PARTICIPANT_HAS_FINISHED_STAGE.getOutMessage(), carName, description);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
