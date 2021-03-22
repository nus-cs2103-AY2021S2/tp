package seedu.address.model.task;

/**
 * Represents the completion status of a task in SOChedule.
 * Guarantees: the status is either COMPLETE or INCOMPLETE.
 */
public class CompletionStatus implements Comparable<CompletionStatus> {
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
     * Updates the completion status of the task to COMPLETE;
     * Guarantees: task is currently incomplete.
     */
    public void markAsDone() {
        completionStatus = Status.COMPLETE;
    }

    /**
     * Returns true if a given string is a valid completion status.
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompletionStatus // instanceof handles nulls
                && completionStatus.equals(((CompletionStatus) other).completionStatus)); // state check
    }

    @Override
    public int hashCode() {
        return completionStatus.hashCode();
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

    @Override
    public int compareTo(CompletionStatus other) {
        if (isComplete() && !other.isComplete()) {
            return 1;
        } else if (!isComplete() && other.isComplete()) {
            return -1;
        } else {
            return 0;
        }
    }
}
