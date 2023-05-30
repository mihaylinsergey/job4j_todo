package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;
import java.util.List;
import java.util.Map;

@ThreadSafe
@Repository
@AllArgsConstructor
public class CategoryStore {

    private final CrudRepository crudRepository;

    public List<Category> findAll() {
        return crudRepository.query("from Category ORDER BY id", Category.class);
    }

    public List<Category> findById(int id) {
        return crudRepository.query("from Category as i where i.id = :fId",
                Category.class, Map.of("fId", id));
    }
}
