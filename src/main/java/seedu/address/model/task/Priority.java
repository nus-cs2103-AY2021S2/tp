package seedu.address.model.task;

public class Priority {
    private int priority;

    /**
     * Constructs an {@code Priority}.
     *
     * @param priority A valid priority.
     */
    public Priority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    public String toString() {
        return Integer.toString(this.priority);
    }
}
