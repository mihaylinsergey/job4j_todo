package ru.job4j.todo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name = "todo_user")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    @EqualsAndHashCode.Include
    private String login;
    @Getter
    @Setter
    @EqualsAndHashCode.Include
    private String password;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
