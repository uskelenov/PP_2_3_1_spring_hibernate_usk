package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
        entityManager.close();
    }

    @Override
    public User showUser(Long id) {
        return listUser().stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void changeUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void clearTableUsers() {
        entityManager.createQuery("DELETE FROM User").executeUpdate();
    }

    @Override
    public List<User> listUser() {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user", User.class);
        return query.getResultList();
    }
}
