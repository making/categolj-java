package am.ik.categolj.app.common.dao;

public interface Sequencer {
    Long incrementAndGet(String key);
}
