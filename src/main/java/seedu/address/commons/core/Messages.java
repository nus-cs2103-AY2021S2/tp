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
    public static final String MESSAGE_TUTOR_DOES_NOT_EXIST = "Tutor of this "
            + "appointment does not exist in the user system. Please ensure tutor "
            + "exists or use the add_tutor function to add a tutor.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the list";
    public static final String MESSAGE_DELETE_APPOINTMENT_FAILURE = "Appointment does"
            + " not exists in appointment list.";
    public static final String MESSAGE_APPOINTMENT_LIST_HAS_TUTOR = "Failed to delete tutor"
            + " because at least one existing appointment has been booked with the"
            + " tutor in question. Please ensure that tutor to be deleted does not"
            + " have any existing appointments.";
    public static final String MESSAGE_APPOINTMENT_LIST_HAS_TUTOR_EDIT = "Failed to "
            + "edit tutor name"
            + " because at least one existing appointment has been booked with the"
            + " tutor in question. Please ensure that tutor whose name is to be "
            + "edited does not have any existing appointments.";

    public static final String MESSAGE_TUTOR_DOES_NOT_TEACH_SUBJECT = "Tutor of the "
            + "appointment in question does not teach this subject. Please ensure "
            + "that tutor teaches this subject: %s";
    public static final String MESSAGE_TIME_FROM_GREATER_THAN = "TIME_FROM must be before TIME_TO. "
            + "Please check your input for TIME_FROM and TIME_TO again.";
    public static final String MESSAGE_INVALID_DATE = "The date entered must not be in the past. "
            + "Please check your input again.";
    public static final String MESSAGE_UNABLE_TO_EDIT_PAST_APPOINTMENT = "Past appointment cannot be edited! "
            + "Please add a new appointment instead.";
    public static final String MESSAGE_UNABLE_TO_EDIT_PAST_SCHEDULE = "Past schedule cannot be edited! "
            + "Please add a new schedule instead.";
    public static final String MESSAGE_UNABLE_TO_EDIT_PAST_REMINDER = "Past reminder cannot be edited! "
            + "Please add a new reminder instead.";
    public static final String MESSAGE_INVALID_TIME_MINUTES = "TIME_FROM and TIME_TO must be in "
            + "blocks of 30 or 60 minutes. Please check your input again.";
    public static final String MESSAGE_INVALID_START_TIME = "The earliest TIME_FROM you can add is 6:00 AM. "
            + "Please check your input again";
    public static final String MESSAGE_INVALID_END_TIME = "The latest TIME_TO you can add is 11:00 PM. "
            + "Please check your input again";
    public static final String MESSAGE_INVALID_SHORT_HOURS = "The shortest timeslot you can add is 1 hour. "
            + "Please check your input again";
    public static final String MESSAGE_INVALID_LONG_HOURS = "The longest timeslot you can add is 8 hours. "
            + "Please check your input again";
    public static final String MESSAGE_DATE_CLASH = "The timeslot you are trying to add clashes with "
            + "another timeslot of an existing appointment or schedule. Please ensure timeslots to not clash.";
    public static final String MESSAGE_MISSING_DATE_FIELD = "The datetime fields (DATE, "
            + "TIME_FROM and TIME_TO) are all required together if any one of them is "
            + "edited. You are missing at least one of these fields.";
    public static final String MESSAGE_ADD_EDIT_COMMAND_ERROR = "Something went wrong when processing your request. "
            + "Please check your inputs again.";
    public static final String MESSAGE_NO_FILTER_PROVIDED = "At least one filter must be provided.";
}
