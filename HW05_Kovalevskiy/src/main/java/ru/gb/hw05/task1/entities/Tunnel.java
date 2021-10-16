package ru.gb.hw05.task1.entities;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw05.task1.contracts.Stage;
import ru.gb.hw05.task1.messages.Messages;

import java.util.concurrent.Semaphore;

@Slf4j
public class Tunnel extends Stage {
    
    private final Semaphore semaphore;
    
    public Tunnel(Semaphore semaphore) {
        this.length = 80;
        this.description = String.format("Тоннель %d метров", length);
        this.semaphore = semaphore;
    }
    
    @Override
    public void go(Car c) {
        String carName = c.getName();
        int carSpeed = c.getSpeed();
        
        try {
            log.info(Messages.PARTICIPANT_IS_PREPARING_FOR_STAGE.getLogMessage(), carName, description);
            System.out.printf(Messages.PARTICIPANT_IS_PREPARING_FOR_STAGE.getOutMessage(), carName, description);
    
            semaphore.acquire();
            log.info(Messages.PARTICIPANT_HAS_STARTED_STAGE.getLogMessage(), carName, description);
            System.out.printf(Messages.PARTICIPANT_HAS_STARTED_STAGE.getOutMessage(), carName, description);

            Thread.sleep(length / carSpeed * 1000L);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            semaphore.release();
            log.info(Messages.PARTICIPANT_HAS_FINISHED_STAGE.getLogMessage(), carName, description);
            System.out.printf(Messages.PARTICIPANT_HAS_FINISHED_STAGE.getOutMessage(), carName, description);
        }
    }
}
