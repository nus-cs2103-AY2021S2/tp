package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

public class Priority {
    private int priority;

    /**
     * Constructs an {@code Priority}.
     *
     * @param priorityString A valid priority.
     */
    public Priority(String priorityString) {
        requireNonNull(priority);
        priority = Integer.parseInt(priorityString);
    }

    public int getPriority() {
        return this.priority;
    }

    public String toString() {
        return Integer.toString(this.priority);
    }
}
