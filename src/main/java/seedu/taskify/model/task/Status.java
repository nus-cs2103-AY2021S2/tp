package seedu.taskify.model.task;

import static java.util.Objects.requireNonNull;

import seedu.taskify.logic.parser.exceptions.ParseException;

/**
 * Represents a Task's status in the address book.
 * Guarantees: immutable; can only be one of 3 status as defined in StatusType.
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS = "Status should be 'uncompleted' or 'completed'";
    public static final String INVALID_STATUS_STRING = "Invalid status!";

    private static final String EXPIRED_STRING = "Expired";
    private static final String UNCOMPLETED_STRING = "Uncompleted";
    private static final String COMPLETED_STRING = "Completed";

    private static final String EXPIRED_VALID_INPUT = "expired";
    private static final String COMPLETED_VALID_INPUT = "completed";
    private static final String UNCOMPLETED_VALID_INPUT = "uncompleted";

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
        status = StatusType.UNCOMPLETED;
    }

    @Override
    public String toString() {
        switch (this.status) {
        case UNCOMPLETED:
            return UNCOMPLETED_STRING;
        case EXPIRED:
            return EXPIRED_STRING;
        case COMPLETED:
            return COMPLETED_STRING;
        default:
            return NULL_STATUS_TYPE; // Will not happen.
        }
    }

    /**
     * Returns true if a given String is a valid status.
     */
    public static boolean isValidStatus(String statusString) {
        return (statusString.equals(UNCOMPLETED_VALID_INPUT)) || (statusString.equals(COMPLETED_VALID_INPUT));
    }

    /**
     * Parses input String and returns one of the corresponding StatusType.
     */
    public static StatusType getStatusType(String statusString) throws ParseException {
        statusString = statusString.toLowerCase();
        switch (statusString) {
        case UNCOMPLETED_VALID_INPUT:
            return StatusType.UNCOMPLETED;
        case EXPIRED_VALID_INPUT:
            return StatusType.EXPIRED;
        case COMPLETED_VALID_INPUT:
            return StatusType.COMPLETED;
        default:
            throw new ParseException(INVALID_STATUS_STRING);
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
