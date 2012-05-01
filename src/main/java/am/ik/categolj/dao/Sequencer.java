package am.ik.categolj.dao;

public interface Sequencer {
    Long incrementAndGet(String key);
}
