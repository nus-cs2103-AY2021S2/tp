package seedu.taskify.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_PARSE_MULTIPLE_INDEX_ON_SINGLE_INDEX = "The string passed to ParserUtil" +
            ".parseMultipleIndex() contains only one argument";
    public static final String MESSAGE_AT_LEAST_ONE_INVALID_INDEX = "At least one Index is not a non-zero unsigned " +
            "integer.";

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_INDEX_RANGE = "Invalid index range given. Second index should be " +
            "bigger than the first index.";
}
