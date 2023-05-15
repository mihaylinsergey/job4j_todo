package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskStore;
import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
public class TaskService {

    private final TaskStore taskStore;

    public TaskService(TaskStore taskStore) {
        this.taskStore = taskStore;
    }

    public List<Task> findAll() {
        return taskStore.findAll();
    }

    public List<Task> findDone() {
        return taskStore.findDone();
    }

    public List<Task> findNew() {
        return taskStore.findNew();
    }

    public void save(Task task) {
        taskStore.save(task);
    }

    public Optional<Task> findById(int id) {
        return taskStore.findById(id);
    }

    public void complete(int id) {
        taskStore.complete(id);
    }

    public void update(Task task) {
        taskStore.update(task);
    }

    public void delete(int id) {
        taskStore.delete(id);
    }
}
