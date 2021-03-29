package seedu.module.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.AppUtil.checkArgument;

public class DoneStatus {
    public static final String MESSAGE_CONSTRAINTS = "Boolean strings should be 'true' or 'false'";

    public final Boolean isDone;
    public final String value;

    /**
     * Constructs a {@code DoneStatus}.
     *
     * @param isDone A valid boolean.
     */
    public DoneStatus(Boolean isDone) {
        requireNonNull(isDone);
        this.isDone = isDone;
        this.value = String.valueOf(isDone);
    }

    /**
     * Constructs a {@code DoneStatus}.
     *
     * @param isDoneString A valid String that is either true or false.
     */
    public DoneStatus(String isDoneString) {
        requireNonNull(isDoneString);
        checkArgument(isValidBooleanString(isDoneString), MESSAGE_CONSTRAINTS);

        this.isDone = parseBooleanString(isDoneString);
        this.value = isDoneString;
    }

    /**
     * Returns true if task is done, false otherwise.
     */
    public Boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns true if a given boolean is a valid boolean.
     */
    public static boolean isValidDoneStatus(String test) {
        return test.equals(String.valueOf(Boolean.TRUE)) || test.equals(String.valueOf(Boolean.FALSE));
    }

    private static Boolean parseBooleanString(String isDoneString) {
        if (isDoneString.equals(String.valueOf(Boolean.TRUE))) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private static Boolean isValidBooleanString(String booleanString) {
        if (booleanString.equals(String.valueOf(Boolean.TRUE))) {
            return Boolean.TRUE;
        } else if (booleanString.equals(String.valueOf(Boolean.FALSE))) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public String toString() {
        return isDone ? "Done" : "Not Done";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneStatus // instanceof handles nulls
                && isDone.equals(((DoneStatus) other).isDone)); // state check
    }

    @Override
    public int hashCode() {
        return isDone.hashCode();
    }
}
