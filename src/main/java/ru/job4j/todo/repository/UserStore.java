package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;
import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class UserStore {

    private final SessionFactory sf;

    public boolean save(User user) {
        boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        Optional<User> rsl = Optional.empty();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            rsl = session.createQuery(
                            "from User as i where i.login = :fLogin and i.password = :fPassword",
                            User.class)
                    .setParameter("fLogin", login)
                    .setParameter("fPassword", password)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }
}
