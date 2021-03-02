package seedu.address.model.task;

public class Priority {
    public int priority;
    public Priority(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return Integer.toString(this.priority);
    }
}
