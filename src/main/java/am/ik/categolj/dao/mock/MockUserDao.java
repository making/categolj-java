package am.ik.categolj.dao.mock;

import am.ik.categolj.dao.UserDao;
import am.ik.cateoglj.entity.User;

public class MockUserDao implements UserDao {

    @Override
    public User authUser(User user) {
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
