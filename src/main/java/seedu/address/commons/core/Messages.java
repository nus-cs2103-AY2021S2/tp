package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_ARGUMENT_FORMAT = "Invalid argument format! \n%1$s";
    public static final String MESSAGE_INVALID_INDEX_ARGUMENT = "The \"i/\" index provided is invalid";
    public static final String MESSAGE_UNKNOWN_GROUP = "The group name provided is not in the group list.";

    // Date
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date format! \n";
    public static final String MESSAGE_DATE_AFTER_TODAY = "Invalid date. Date %1$s is after today!";
    public static final String MESSAGE_BIRTHDAY_AFTER_TODAY = "Invalid birthday. Birthday %1$s is after today!";
    public static final String MESSAGE_DATE_BEFORE_BIRTHDAY = "Invalid date. Person is not yet born on %1$s!";

    // Time
    public static final String MESSAGE_INVALID_TIME_FORMAT = "Invalid time format! \n";
    public static final String MESSAGE_TIME_AFTER_NOW = "Invalid time. Time %1$s is after current time now!";

    // Files
    public static final String MESSAGE_INVALID_FILE = "\"%1$s\" is an invalid file path";
    public static final String MESSAGE_FILE_NOT_FOUND = "File \"%1$s\" not found";
    public static final String MESSAGE_FILE_TOO_BIG = "File \"%1$s\" is too big. Reduce file size to below %2$s bytes";
    public static final String MESSAGE_INVALID_FILE_EXTENSION = "File \"%1$s\" does not have the required extension. "
        + "Accepted file extensions: \"%2$s\"";
    public static final String MESSAGE_INVALID_FILE_SIGNATURE = "File \"%1$s\" is not a valid \"%2$s\" file";

    // Special Dates
    public static final String MESSAGE_NO_DATES = "%1$s has no special dates to delete.";

    // Tabs
    public static final String MESSAGE_INVALID_TAB = "To view this tab, use the following command instead: \n%1$s";
}
