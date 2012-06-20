package am.ik.categolj.app.dao.impl.mock;

import org.springframework.stereotype.Repository;

import am.ik.categolj.app.dao.UserDao;
import am.ik.categolj.app.domain.User;

@Repository
public class MockUserDao implements UserDao {

    @Override
    public User authUser(User user) {
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

}
