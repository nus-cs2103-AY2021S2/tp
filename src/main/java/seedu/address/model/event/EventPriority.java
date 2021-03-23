package seedu.address.model.event;

public enum EventPriority {
    HIGH,
    MEDIUM,
    LOW,
    NONE;

    public static final String MESSAGE_CONSTRAINTS =
            "Event priority should only be HIGH, MEDIUM or LOW";
}
