package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.repository.PriorityStore;
import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
@AllArgsConstructor
public class PriorityService {

    private final PriorityStore priorityStore;

    public List<Priority> findAll() {
        return priorityStore.findAll();
    }

    public List<Priority> findByName(String name) {
        return priorityStore.findByName(name);
    }
}
