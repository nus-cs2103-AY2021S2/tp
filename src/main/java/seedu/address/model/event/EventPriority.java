package seedu.address.model.event;

import java.util.Locale;

public enum EventPriority {
    HIGH("HIGH", "H"),
    MEDIUM("MEDIUM", "M"),
    LOW("LOW", "L");

    private final String priority;
    private final String shorthand;

    EventPriority(String priority, String shorthand) {
        this.priority = priority;
        this.shorthand = shorthand;
    }

    /**
     * Tries to find a matching EventPriority from priority or shorthand, else returns null
     * @param testPriority String to be tested to see if it's an EventPriority
     * @return EventPriority or null
     */
    public static EventPriority valueOfPriority(String testPriority) {
        String testPriorityTrimmed = testPriority.trim().toUpperCase();
        for (EventPriority eventPriority : values()) {
            if (eventPriority.priority.equals(testPriorityTrimmed)
                    || eventPriority.shorthand.equals(testPriorityTrimmed)) {
                return eventPriority;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.priority;
    }

    public static final String MESSAGE_CONSTRAINTS = "Event priority should be High (h), Medium (m), or Low (l)";
}
