package seedu.address.model.tag;

public enum State {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private final int priorityValue;

    State(int priorityValue) {
        this.priorityValue = priorityValue;
    }

    public int getPriorityValue() {
        return priorityValue;
    }

}
