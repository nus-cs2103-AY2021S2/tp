package seedu.address.model.task;

/**
 * Enum for the priority values
 * Priority can talk on the values, low, medium, high and unassigned
 * It is used to signify the priority of a task
 * When the user specifies the value of priority, user can only choose from low, medium or high
 * Unassigned is the value that is set if there is no user input for priority
 * */
public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low"),
    UNASSIGNED("unassigned");

    public static final String MESSAGE_CONSTRAINTS = "Error with Task Priority! "
            + "Task Priority should take one of the predefined values: high, medium or low";
    public static final String MESSAGE_CANNOT_SPECIFY_UNASSIGNED = "If you are specifying a value fot priority"
            + "it cannot be unassigned. Please choose from the value: high, medium or low. ";

    public final String priority;


    Priority(String priority) {
        this.priority = priority;
    }

    public static String getEnumName() {
        return "Priority";
    }

    public static boolean isUnassigned(String str) {
        return str.equals("unassigned");
    }

    /**
     * Returns a boolean value by check if the String value is a
     * value within this enum and is not unassigned value
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

    public String getPriority() {
        return this.priority.toLowerCase();
    }

    @Override
    public String toString() {
        return this.priority;
    }
}
