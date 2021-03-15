package seedu.address.model.task;

public class CompletionStatus {
    public static final String VALIDATION_REGEX = "COMPLETE|INCOMPLETE";
    public static final String MESSAGE_CONSTRAINTS =
            "Completion Statuses should only either by \"COMPLETE\" or \"INCOMPLETE\"";

    private Status completionStatus;

    enum Status {
        COMPLETE,
        INCOMPLETE
    }

    /**
     * Constructs an {@code CompletionStatus}.
     */
    public CompletionStatus() {
        completionStatus = Status.INCOMPLETE;
    }

    /**
     * Constructs an {@code CompletionStatus}.
     *
     * @param cs A valid completion status ("COMPLETE" or "INCOMPLETE").
     */
    public CompletionStatus(String cs) {
        switch (cs) {
        case "COMPLETE":
            completionStatus = Status.COMPLETE;
            break;

        case "INCOMPLETE":
            completionStatus = Status.INCOMPLETE;
            break;

        default:
            // should not reach here since validation is pre-done.
            break;
        }
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
     * Returns true if a given string is a valid completion status.
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
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
