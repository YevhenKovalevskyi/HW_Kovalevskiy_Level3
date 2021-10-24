package ru.gb.hw07.task1;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw07.task1.messages.Messages;
import ru.gb.hw07.task1.tests.FirstTest;
import ru.gb.hw07.task1.tests.ThirdTest;

@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.error(Messages.START_PROGRAM.getLogMessage());
        System.out.println(Messages.START_PROGRAM.getOutMessage());
        
        Tests.start(FirstTest.class);
        System.out.println("-----------");
        Tests.start("SecondTest");
        System.out.println("-----------");
        Tests.start(ThirdTest.class);
        System.out.println("-----------");
        Tests.start("FourthTest");
    
        log.error(Messages.END_PROGRAM.getLogMessage());
        System.out.println(Messages.END_PROGRAM.getOutMessage());
    }
}
