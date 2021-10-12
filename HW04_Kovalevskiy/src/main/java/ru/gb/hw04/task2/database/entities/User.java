package ru.gb.hw04.task2.database.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class User extends Entity {
    
    private Integer id;
    private String login;
    private String name;
    private String email;
    private int age;
    private String sex;
    private String updated_at;
}