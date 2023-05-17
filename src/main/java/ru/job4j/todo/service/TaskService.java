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

    public List<Task> findDoneOrNew(boolean flag) {
        return taskStore.findDoneOrNew(flag);
    }

    public boolean save(Task task) {
       return taskStore.save(task);
    }

    public Optional<Task> findById(int id) {
        return taskStore.findById(id);
    }

    public boolean complete(int id) {
        return taskStore.complete(id);
    }

    public boolean update(Task task) {
        return taskStore.update(task);
    }

    public boolean delete(int id) {
        return taskStore.delete(id);
    }
}
