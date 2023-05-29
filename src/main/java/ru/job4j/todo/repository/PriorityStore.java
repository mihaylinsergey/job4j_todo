package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class PriorityStore {

    private final CrudRepository crudRepository;

    public List<Priority> findAll() {
        return crudRepository.query("from Priority ORDER BY id", Priority.class);
    }

    public List<Priority> findByName(String name) {
        return crudRepository.query("from Priority as i where i.name = :fName",
                Priority.class, Map.of("fName", name));
    }
}
