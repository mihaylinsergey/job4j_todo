package ru.job4j.todo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tasks")
@Data
public class Task {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String description;
   private LocalDateTime created;
   private boolean done;
}
