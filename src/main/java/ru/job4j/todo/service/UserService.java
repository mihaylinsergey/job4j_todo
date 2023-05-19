package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserStore;
import java.util.Optional;

@ThreadSafe
@Service
@AllArgsConstructor
public class UserService {

    private final UserStore userTask;

    public boolean save(User user) {
        return userTask.save(user);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userTask.findByLoginAndPassword(login, password);
    }
}
