package ru.gb.hw05.task1.entities;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.gb.hw05.task1.Race;
import ru.gb.hw05.task1.messages.Messages;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Car implements Runnable {
    
    private static int CARS_COUNT = 0;
    
    private final Race race;
    @Getter private final int speed;
    @Getter private final String name;
    private final CountDownLatch startLatch;
    private final CountDownLatch finishLatch;
    
    public Car(Race race, int speed, String name, CountDownLatch startLatch, CountDownLatch finishLatch) {
        this.race = race;
        this.name = name;
        this.speed = speed;
        this.startLatch = startLatch;
        this.finishLatch = finishLatch;
    
        CARS_COUNT++;
    }
    
    @Override
    public void run() {
        try {
            log.info(Messages.PARTICIPANT_PREPARING_FOR_RACE.getLogMessage(), name);
            System.out.printf(Messages.PARTICIPANT_PREPARING_FOR_RACE.getOutMessage(), name);
            
            Thread.sleep(500 + (int)(Math.random() * 800));
    
            log.info(Messages.PARTICIPANT_HAS_PREPARED_FOR_RACE.getLogMessage(), name);
            System.out.printf(Messages.PARTICIPANT_HAS_PREPARED_FOR_RACE.getOutMessage(), name);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    
        startLatch.countDown();
        
        try {
            startLatch.await();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        
        race.getStages().forEach(stage -> {
            stage.go(this);
        });
    
        if (finishLatch.getCount() == CARS_COUNT) {
            log.info(Messages.PARTICIPANT_HAS_WON_RACE.getLogMessage(), name);
            System.out.printf(Messages.PARTICIPANT_HAS_WON_RACE.getOutMessage(), name);
        }
    
        finishLatch.countDown();
    }
}
