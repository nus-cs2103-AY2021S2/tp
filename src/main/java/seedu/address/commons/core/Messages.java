package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "Index should be positive and within the range of"
            + " the list.\nAdditionally, at least 1 prefix must be provided.";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!\n";
    public static final String MESSAGE_CALENDAR_SHOWING_CURRENT_MONTH = "Calendar is now showing this month.\n";
    public static final String MESSAGE_INVALID_PREAMBLE = "Your command might have spaces in your command word, "
            + "or your first prefix is invalid.";
    public static final String MESSAGE_INVALID_PREFIX = "Invalid prefix entered.\n";

}
