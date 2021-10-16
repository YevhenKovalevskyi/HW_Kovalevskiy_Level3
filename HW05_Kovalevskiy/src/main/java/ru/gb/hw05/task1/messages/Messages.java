package ru.gb.hw05.task1.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Messages Enum represents text messages for use and localization(if it needed)
 */
@AllArgsConstructor
public enum Messages {
    START_PROGRAM("Start program...", "\nStart program...\n"),
    END_PROGRAM("End program.\n", "End program.\n"),
    
    RACE_PREPARATION(
            "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!", "\nВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!\n\n"
    ),
    RACE_HAS_STARTED(
            "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!", "\nВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!\n\n"
    ),
    RACE_HAS_FINISHED(
            "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!", "\nВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!\n\n"
    ),
    PARTICIPANT_IS_PREPARING_FOR_STAGE(
            "{} готовится к этапу(ждет): {}", "%s готовится к этапу(ждет): %s\n"
    ),
    PARTICIPANT_HAS_STARTED_STAGE(
            "{} начал этап: {}", "%s начал этап: %s\n"
    ),
    PARTICIPANT_HAS_FINISHED_STAGE(
            "{} закончил этап: {}", "%s закончил этап: %s\n"
    ),
    PARTICIPANT_PREPARING_FOR_RACE(
            "{} готовится", "%s готовится\n"
    ),
    PARTICIPANT_HAS_PREPARED_FOR_RACE(
            "{} готов", "%s готов\n"
    ),
    PARTICIPANT_HAS_WON_RACE(
            "{} WIN!!!", "\n%s WIN!!!\n\n"
    );
    
    @Getter
    private String logMessage;
    @Getter private String outMessage;
}
