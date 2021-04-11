package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event at this index does not exist.";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task at this index does not exist.";
    public static final String MESSAGE_EVENTS_LISTED_OVERVIEW = "%1$d event(s) listed!";
    public static final String MESSAGE_PAST_EVENT_END_DATE_TIME = "The event end date and time provided are past.";
    public static final String MESSAGE_PAST_DEADLINE = "The deadline provided should not be earlier than today.";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d task(s) listed!";
    public static final String MESSAGE_END_DATETIME_BEFORE_START_DATETIME = "The event end date and time "
            + "should be after start date and time!";
    public static final String MESSAGE_INVALID_DATE = "The date format is invalid! "
            + "Date should be in the format of YYYY-MM-DD!";
    public static final String MESSAGE_DATE_PASSED = "The date is in the past! "
            + "Please key in a date from today onwards!";

}
