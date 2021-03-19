package seedu.address.model.task;

public enum TaskStatus {
    UNCOMPLETED("Uncompleted"),
    COMPLETED("Completed");

    public static final String MESSAGE_CONSTRAINTS = "Task is an Enum and should take on one of the predefined values!";

    public final String status;


    TaskStatus(String status) {
        this.status = status;
    }

    public static String getEnumName() {
        return "Task Status";
    }

    public static boolean isValidValue(String value) {
        for (TaskStatus c : TaskStatus.values()) {
            if (c.name().equals(value)) {
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
