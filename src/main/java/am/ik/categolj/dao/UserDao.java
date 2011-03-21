package am.ik.categolj.dao;

import am.ik.cateoglj.entity.User;

public interface UserDao {
    User authUser(User user);

    User getUserById(Long id);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
