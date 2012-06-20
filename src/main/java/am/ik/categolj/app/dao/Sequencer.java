package am.ik.categolj.app.dao;

public interface Sequencer {
    Long incrementAndGet(String key);
}
