package ru.job4j.todo.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
   @ManyToOne
   @JoinColumn(name = "user_id")
   @Getter
   @Setter
   private User user;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "priority_id")
   @Getter
   @Setter
   private Priority priority;
   @ManyToMany
   @JoinTable(
           name = "tasks_categories",
           joinColumns = {@JoinColumn(name = "task_id")},
           inverseJoinColumns = {@JoinColumn(name = "category_id")}
   )
   @Getter
   @Setter
   private List<Category> categories = new ArrayList<>();
}
