package am.ik.categolj.domain.repository.user;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;

import am.ik.categolj.domain.model.User;
import am.ik.yalf.logger.Logger;

@Repository
public class MongoUserRepository implements UserRepository {
    private static final Logger logger = Logger.getLogger(MongoUserRepository.class);
    @Inject
    private Datastore ds;

    @Override
    public User getUserByName(String name) {
        Query<User> q = ds.find(User.class, "name", name);
        logger.debug(false, "search user {0}", q);
        return q.get();
    }

    @Override
    public void insertUser(User user) {
        ds.save(user);
    }

    @Override
    public void updateUser(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(User user) {
        // TODO Auto-generated method stub

    }

}
