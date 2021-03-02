package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

public class Priority {
    private int priority;

    /**
     * Constructs an {@code Priority}.
     *
     * @param priority A valid priority.
     */
    public Priority(Integer priority) {
        requireNonNull(priority);
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    public String toString() {
        return Integer.toString(this.priority);
    }
}
