package seedu.heymatez.model.task;

/**
 * Enum for the priority values
 * Priority can take on the values, LOW, MEDIUM, HIGH, UNASSIGNED and is used to signify the priority of a task
 * UNASSIGNED is the value that is set if there is no user input for priority
 * */
public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low"),
    UNASSIGNED("unassigned");

    public static final String MESSAGE_CONSTRAINTS = "Task Priority should take one of the predefined values: "
            + "high, medium, low or unassigned (case-sensitive)";

    public final String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    /**
     * Returns a String value by of the Enum name to use for UI.
     *
     * @return a string value of the enum name to us for the UI
     */
    public static String getEnumName() {
        return "Priority";
    }

    /**
     * Returns a boolean value if the str input is "unasssigned".
     *
     * @return a boolean value
     */
    public static boolean isUnassigned(String str) {
        return str.equals("unassigned");
    }

    /**
     * Returns a boolean value by check if the String value is a
     * value within this enum and is not unassigned value.
     *
     * @param value value for the Task Status enum
     * @return a boolean value, whether the it is a value inside this enum
     */
    public static boolean isValidValue(String value) {
        for (Priority c : Priority.values()) {

            boolean match = c.name().toLowerCase().equals(value);

            if (match) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a string value of the priority casted to lower cast
     *
     * @return a string value representing the priority
     */
    public String getPriority() {
        return this.priority.toLowerCase();
    }

    @Override
    public String toString() {
        return this.priority;
    }
}
