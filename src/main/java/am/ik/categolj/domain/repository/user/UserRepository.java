package am.ik.categolj.domain.repository.user;

import am.ik.categolj.domain.model.User;

public interface UserRepository {
    User getUserByName(String name);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
