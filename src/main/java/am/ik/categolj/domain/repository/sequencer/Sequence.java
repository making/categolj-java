package am.ik.categolj.domain.repository.sequencer;

import com.google.code.morphia.annotations.Entity;

@Entity(value = "seq", noClassnameStored = true)
public class Sequence {
    private String key;
    private Long value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Seqence [key=" + key + ", value=" + value + "]";
    }
}
