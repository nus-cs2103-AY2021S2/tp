package seedu.address.model.event;

public enum EventStatus {
    BACKLOG,
    TODO,
    IN_PROGRESS,
    DONE;

    public static final String STRING_BACKLOG = "BACKLOG";
    public static final String STRING_TODO = "TODO";
    public static final String STRING_IN_PROGRESS = "IN PROGRESS";
    public static final String STRING_DONE = "DONE";
    public static final String MESSAGE_CONSTRAINTS = "Event status should be BACKLOG, TODO, "
            + "IN_PROGRESS, or DONE";

}
