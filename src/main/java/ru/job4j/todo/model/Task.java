package ru.job4j.todo.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Getter
   @Setter
   @EqualsAndHashCode.Include
   private int id;
   @Getter
   @Setter
   private String description;
   @Getter
   @Setter
   private LocalDateTime created;
   @Getter
   @Setter
   private boolean done;
}
