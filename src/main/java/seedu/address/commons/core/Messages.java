package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX = "The schedule index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The appointment index provided is invalid";
    public static final String MESSAGE_APPOINTMENT_LISTED_OVERVIEW = "%1$d appointments listed!";
    public static final String MESSAGE_INVALID_GRADE_DISPLAYED_INDEX =
            "The grade index provided is invalid";
    public static final String MESSAGE_TIME_FROM_GREATER_THAN = "Time from has to be "
            + "smaller than time to. Please check your input for time from and time to "
            + "again.";
    public static final String MESSAGE_TUTOR_DOES_NOT_EXIST = "Tutor of this "
            + "appointment does not exist in the user system. Please ensure tutor "
            + "exists or use the add_tutor function to add a tutor.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the list";
    public static final String MESSAGE_DELETE_APPOINTMENT_FAILURE = "Appointment does"
            + " not exists in appointment list.";
}
