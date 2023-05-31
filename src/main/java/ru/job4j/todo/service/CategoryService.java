package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.CategoryStore;
import java.util.List;

@ThreadSafe
@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryStore categoryStore;

    public List<Category> findAll() {
        return categoryStore.findAll();
    }

    public List<Category> findById(int id) {
        return categoryStore.findById(id);
    }

    public List<Category> findByLislId(List<Integer> listId) {
        return categoryStore.findByLislId(listId);
    }
}
