package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User User);

    User showUser(Long id);

    void deleteUser(Long id);

    void changeUser(User user);

    void clearTableUsers();

    List<User> listUser();


}
