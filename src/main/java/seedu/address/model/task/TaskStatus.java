package seedu.address.model.task;

public enum TaskStatus {
    UNCOMPLETED("uncompleted"),
    COMPLETED("completed");

    public static final String MESSAGE_CONSTRAINTS = "Task is an Enum and should take on one of the predefined values!";

    public final String status;


    TaskStatus(String status) {
        this.status = status;
    }

    public static String getEnumName() {
        return "Task Status";
    }

    /**
     * Returns a boolean value by check if the String value is a
     * value within this enum
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

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
