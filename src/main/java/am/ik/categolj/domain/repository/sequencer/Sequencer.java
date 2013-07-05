package am.ik.categolj.domain.repository.sequencer;

public interface Sequencer {
    Long incrementAndGet(String key);
}
