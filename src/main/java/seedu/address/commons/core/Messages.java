package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX = "The project index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

    public static final String MESSAGE_ADD_EVENT_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_ADD_TODO_SUCCESS = "New todo added: %1$s";
    public static final String MESSAGE_ADD_DEADLINE_SUCCESS = "New deadline added: %1$s";

    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in this project.";
    public static final String MESSAGE_DUPLICATE_TODO = "This todo already exists in this project.";
    public static final String MESSAGE_DUPLICATE_DEADLINE = "This deadline already exists in this project.";

    public static final String MESSAGE_DELETE_DEADLINE_SUCCESS = "Deleted Deadline: %1$s";
    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted Event: %1$s";
    public static final String MESSAGE_DELETE_TODO_SUCCESS = "Deleted Todo: %1$s";

    public static final String MESSAGE_PARSER_DESCRIPTION_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String MESSAGE_PARSER_INTERVAL_CONSTRAINTS =
            "Interval should be one of: NONE, DAILY, WEEKLY, FORTNIGHTLY, MONTHLY, YEARLY";
    public static final String MESSAGE_PARSER_DATE_CONSTRAINTS =
            "Date should be in the form dd-MM-yyyy";

}
