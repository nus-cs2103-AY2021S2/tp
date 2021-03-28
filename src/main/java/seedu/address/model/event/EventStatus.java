package seedu.address.model.event;

public enum EventStatus {
    BACKLOG("BACKLOG", "BL"),
    TODO("TODO", "TD"),
    IN_PROGRESS("IN_PROGRESS", "IP"),
    DONE("DONE", "D");

    public static final String STRING_BACKLOG = "BACKLOG";
    public static final String STRING_DONE = "DONE";
    public static final String STRING_IN_PROGRESS = "IN PROGRESS";
    public static final String STRING_TODO = "TODO";
    public static final String MESSAGE_CONSTRAINTS = "Event status should be BACKLOG (bl), TODO (td), "
            + "IN_PROGRESS (ip), or DONE (d)";

    private final String status;
    private final String shorthand;

    EventStatus(String status, String shorthand) {
        this.status = status;
        this.shorthand = shorthand;
    }

    /**
     * Tries to find a matching EventStatus from status or shorthand, else returns null
     * @param testStatus String to be tested to see if it's an EventStatus
     * @return EventStatus or null
     */
    public static EventStatus valueOfStatus(String testStatus) {
        String testStatusTrimmed = testStatus.trim().toUpperCase();
        for (EventStatus eventStatus : values()) {
            if (eventStatus.status.equals(testStatusTrimmed)
                    || eventStatus.shorthand.equals(testStatusTrimmed)) {
                return eventStatus;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.status;
    }

}
