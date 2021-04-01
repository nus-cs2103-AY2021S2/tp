package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX = "The schedule index provided is invalid";
    public static final String MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX = "The reminder index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The appointment index provided is invalid";
    public static final String MESSAGE_APPOINTMENT_LISTED_OVERVIEW = "%1$d appointments listed!";
    public static final String MESSAGE_INVALID_GRADE_DISPLAYED_INDEX =
            "The grade index provided is invalid";
    public static final String MESSAGE_TIME_FROM_GREATER_THAN = "TIME_FROM must be before TIME_TO. "
            + "Please check your input for TIME_FROM and TIME_TO again.";
    public static final String MESSAGE_INVALID_DATE = "The new date must not be in the past. "
            + "Please check your input again.";
}
