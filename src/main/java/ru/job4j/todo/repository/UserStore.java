package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class UserStore {

    private final CrudRepository crudRepository;

    public boolean save(User user) {
        return crudRepository.run(session -> session.persist(user));
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                "from User as i where i.login = :fLogin and i.password = :fPassword",
                User.class, Map.of("fLogin", login, "fPassword", password));
    }
}
