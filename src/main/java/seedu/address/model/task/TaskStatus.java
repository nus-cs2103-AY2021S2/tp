package seedu.address.model.task;

/**
 * Enum for the Task status values.
 * It is used to signify whether a task has been completed or uncompleted.
 * Task Status can take on the values, UNCOMPLETED or COMPLETED.
 * */
public enum TaskStatus {
    UNCOMPLETED("uncompleted"),
    COMPLETED("completed");

    public static final String MESSAGE_CONSTRAINTS = "Error with Task Status Value. "
            + "Task Status should take one of the predefined values: completed or uncompleted (Case sensitive)";

    public final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    /**
     * Returns a String value by of the Enum name to use for UI.
     *
     * @return a string value of the enum name to us for the UI
     */
    public static String getEnumName() {
        return "Task Status";
    }

    /**
     * Returns a boolean value by check if the String value is a
     * value within this enum.
     *
     * @param value value for the Task Status enum
     * @return a boolean value, whether the it is a value inside this enum
     */
    public static boolean isValidValue(String value) {
        for (TaskStatus c : TaskStatus.values()) {
            if (c.name().toLowerCase().equals(value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a string value of the TaskStatus casted to lower cast
     *
     * @return a string value representing the TaskStatus
     */
    public String getStatus() {
        return this.status.toLowerCase();
    }

    @Override
    public String toString() {
        return this.status;
    }
}
