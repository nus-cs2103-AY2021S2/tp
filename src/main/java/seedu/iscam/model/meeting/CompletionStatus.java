package seedu.iscam.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.util.AppUtil.checkArgument;

public class CompletionStatus {
    public enum Status {
        COMPLETED,
        INCOMPLETE
    }
    public static final String ARGUMENT_COMPLETE = "complete";
    public static final String ARGUMENT_INCOMPLETE = "incomplete";
    public static final String TEXT_COMPLETE = "Completed";
    public static final String TEXT_INCOMPLETE = "Not completed";
    public static final String MESSAGE_CONSTRAINTS = "Completion status should only be [" + ARGUMENT_COMPLETE + "] or ["
            + ARGUMENT_INCOMPLETE + "].";

    public final Status value;

    public CompletionStatus(Status status) {
        requireNonNull(status);
        this.value = status;
    }

    public CompletionStatus(String str) {
        requireNonNull(str);
        checkArgument(isStringValid(str), MESSAGE_CONSTRAINTS);
        this.value = str.equals(ARGUMENT_COMPLETE) ? Status.COMPLETED : Status.INCOMPLETE;
    }

    public static boolean isStringValid(String str) {
        return str.equals(ARGUMENT_COMPLETE) || str.equals(ARGUMENT_INCOMPLETE);
    }

    public Status get() {
        return value;
    }

    public boolean isComplete() {
        return value == Status.COMPLETED;
    }

    public CompletionStatus complete() {
        return new CompletionStatus(Status.COMPLETED);
    }

    public String toDisplayString() {
        return value == Status.COMPLETED ? TEXT_COMPLETE : TEXT_INCOMPLETE;
    }

    @Override
    public String toString() {
        return value == Status.COMPLETED ? ARGUMENT_COMPLETE : ARGUMENT_INCOMPLETE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof CompletionStatus
                && value.equals(((CompletionStatus) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
