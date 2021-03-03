package seedu.taskify.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's status in the address book.
 * Guarantees: immutable; can only be one of 3 status as defined in StatusType.
 */
public class Status {

    private static final String NOT_DONE_STRING = "Not done";
    private static final String IN_PROGRESS_STRING = "In progress";
    private static final String COMPLETED_STRING = "Completed";

    private static final String NULL_STATUS_TYPE = "Error, status does not have a status type!";

    public final StatusType status;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(StatusType status) {
        requireNonNull(status);
        this.status = status;
    }

    /**
     * Constructs a {@code Status} with default StatusType.NOT_DONE.
     */
    public Status() {
        status = StatusType.NOT_DONE;
    }

    @Override
    public String toString() {
        switch (this.status) {
        case NOT_DONE:
            return NOT_DONE_STRING;
        case IN_PROGRESS:
            return IN_PROGRESS_STRING;
        case COMPLETED:
            return COMPLETED_STRING;
        default:
            return NULL_STATUS_TYPE; // Will not happen
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                       || (other instanceof Status // instanceof handles nulls
                                   && status.equals(((Status) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

}
