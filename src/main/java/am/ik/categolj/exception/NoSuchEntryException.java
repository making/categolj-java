package am.ik.categolj.exception;

@SuppressWarnings("serial")
public class NoSuchEntryException extends RuntimeException {
    private final Long entryId;

    public NoSuchEntryException(Long entryId) {
        this.entryId = entryId;
    }

    public NoSuchEntryException(Long entryId, String message) {
        super(message);
        this.entryId = entryId;
    }

    public NoSuchEntryException(Long entryId, Throwable cause) {
        super(cause);
        this.entryId = entryId;
    }

    public NoSuchEntryException(Long entryId, String message, Throwable cause) {
        super(message, cause);
        this.entryId = entryId;
    }

    public Long getEntryId() {
        return entryId;
    }

}
