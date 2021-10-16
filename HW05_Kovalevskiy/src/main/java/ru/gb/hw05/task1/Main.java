package ru.gb.hw05.task1;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw05.task1.entities.*;
import ru.gb.hw05.task1.messages.Messages;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

@Slf4j
public class Main {
    
    public static final int CARS_COUNT = 4;
    private static final int TUNNEL_THROUGHPUT = 2;
    
    private static final CountDownLatch startLatch = new CountDownLatch(CARS_COUNT);
    private static final CountDownLatch finishLatch = new CountDownLatch(CARS_COUNT);
    private static final Semaphore semaphore = new Semaphore(TUNNEL_THROUGHPUT, true);
    
    public static void main(String[] args) {
        log.info(Messages.START_PROGRAM.getLogMessage());
        System.out.print(Messages.START_PROGRAM.getOutMessage());
    
        log.info(Messages.RACE_PREPARATION.getLogMessage());
        System.out.print(Messages.RACE_PREPARATION.getOutMessage());
        
        Race race = new Race(new Road(60), new Tunnel(semaphore), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        
        for (int i = 0; i < cars.length; i++) {
            int carSpeed = 20 + (int) (Math.random() * 10);
            String carName = String.format("Участник #%d", i + 1);
            
            cars[i] = new Car(race, carSpeed, carName, startLatch, finishLatch);
        }
    
        for (Car car : cars) {
            new Thread(car).start();
        }
    
        try {
            startLatch.await();
            log.info(Messages.RACE_HAS_STARTED.getLogMessage());
            System.out.print(Messages.RACE_HAS_STARTED.getOutMessage());
            
            finishLatch.await();
            log.info(Messages.RACE_HAS_FINISHED.getLogMessage());
            System.out.print(Messages.RACE_HAS_FINISHED.getOutMessage());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    
        log.info(Messages.END_PROGRAM.getLogMessage());
        System.out.print(Messages.END_PROGRAM.getOutMessage());
    }
}
