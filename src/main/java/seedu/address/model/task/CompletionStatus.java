package seedu.address.model.task;

enum Status {
    COMPLETE,
    INCOMPLETE
}

public class CompletionStatus {
    private Status completionStatus;

    /**
     * Constructs an {@code CompletionStatus}.
     */
    public CompletionStatus() {
        completionStatus = Status.INCOMPLETE;
    }

    public boolean isComplete() {
        return completionStatus == Status.COMPLETE;
    }

    /**
     * Flips the completion status.
     */
    public void flipCompletionStatus() {
        if (completionStatus == Status.COMPLETE) {
            completionStatus = Status.INCOMPLETE;
        } else {
            completionStatus = Status.COMPLETE;
        }
    }

    /**
     * Returns a String representation of {@code CompletionStatus}.
     */
    public String toString() {
        if (completionStatus == Status.COMPLETE) {
            return "Complete";
        } else {
            return "Incomplete";
        }
    }
}
