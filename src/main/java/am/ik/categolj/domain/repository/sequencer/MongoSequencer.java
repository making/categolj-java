package am.ik.categolj.domain.repository.sequencer;

import javax.inject.Inject;

import org.springframework.stereotype.Component;


import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

@Component
public class MongoSequencer implements Sequencer {
    @Inject
    protected Datastore ds;

    @Override
    public Long incrementAndGet(String key) {
        Query<Sequence> q = ds.find(Sequence.class).filter("key", key);
        UpdateOperations<Sequence> ops = ds.createUpdateOperations(
                Sequence.class).inc("value", 1);
        Sequence seq = ds.findAndModify(q, ops, false, true);
        return seq.getValue();
    }

    public void setDs(Datastore ds) {
        this.ds = ds;
    }
}
