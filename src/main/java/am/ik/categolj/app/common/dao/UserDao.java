package am.ik.categolj.app.common.dao;

import am.ik.categolj.app.common.domain.User;

public interface UserDao {
    User authUser(User user);

    User getUserById(Long id);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
