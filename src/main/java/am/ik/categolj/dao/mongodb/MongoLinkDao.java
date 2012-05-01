package am.ik.categolj.dao.mongodb;

import java.util.List;

import javax.inject.Inject;

import am.ik.categolj.dao.LinkDao;
import am.ik.categolj.entity.Link;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;

public class MongoLinkDao implements LinkDao {

    @Inject
    protected Datastore ds;

    @Inject
    protected MongoSequencer mongoSequencer;

    @Override
    public List<Link> findAll() {
        Query<Link> q = ds.createQuery(Link.class).order("order,-id");
        return q.asList();
    }

    @Override
    public void save(Link link) {
        if (link.getId() == null) {
            Long id = mongoSequencer.incrementAndGet("link");
            link.setId(id);
        }
        ds.save(link);
    }

    @Override
    public void delete(Long id) {
        ds.delete(Link.class, id);
    }

    public void setDs(Datastore ds) {
        this.ds = ds;
    }

    public void setMongoSequencer(MongoSequencer mongoSequencer) {
        this.mongoSequencer = mongoSequencer;
    }
}
