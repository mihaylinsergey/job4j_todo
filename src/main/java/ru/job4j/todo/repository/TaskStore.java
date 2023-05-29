package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class TaskStore {
    private final CrudRepository crudRepository;

    public List<Task> findAll() {
        return crudRepository.query("from Task i JOIN FETCH i.priority ORDER BY i.id", Task.class);
    }

    public List<Task> findDoneOrNew(boolean flag) {
        return crudRepository.query("from Task as i JOIN FETCH i.priority where i.done = :fDone",
                Task.class, Map.of("fDone", flag));
    }

    public boolean save(Task task) {
        task.setCreated(LocalDateTime.now());
        return crudRepository.run(session -> session.persist(task));
   }

    public Optional<Task> findById(int id) {
        return crudRepository.optional(Task.class, id);
    }

    public boolean complete(int id) {
        return crudRepository.run("UPDATE Task SET done = :fDone WHERE id = :fId",
                Map.of("fDone", true, "fId", id));
    }

    public boolean update(Task task) {
        return crudRepository.run("UPDATE Task SET description = :fDescription WHERE id = :fId",
                Map.of("fDescription", task.getDescription(), "fId", task.getId()));
    }

    public boolean delete(int id) {
        return crudRepository.run("DELETE Task WHERE id = :fId",
                Map.of("fId", id));
    }
}

