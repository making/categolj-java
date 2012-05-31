package am.ik.categolj.app.common.dao.impl.mock;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import am.ik.categolj.app.common.dao.Sequencer;

@Repository
public class MockSequcencer implements Sequencer {
    private final AtomicLong seqencer = new AtomicLong();

    @Override
    public Long incrementAndGet(String key) {
        return seqencer.incrementAndGet();
    }

    public AtomicLong getSeqencer() {
        return seqencer;
    }

}
