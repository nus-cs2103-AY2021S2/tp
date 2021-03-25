package seedu.address.model.event;

public enum EventStatus {
    BACKLOG,
    TODO,
    IN_PROGRESS,
    DONE;

    public static final String MESSAGE_CONSTRAINTS = "Event status should be BACKLOG, TODO, "
            + "IN_PROGRESS, or DONE";
}
