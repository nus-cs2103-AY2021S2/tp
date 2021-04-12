package seedu.us.among.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_COMMAND_ERROR = "%1$s\n";
    public static final String MESSAGE_INDEX_NOT_WITHIN_LIST =
             "Index provided is not within the saved endpoint list.\n";
    public static final String MESSAGE_INVALID_INDEX = "An index must be specified as an unsigned integer "
            + "greater than 0 and less than or equal to the number of endpoints in the endpoint list.\n";
    public static final String MESSAGE_ENDPOINTS_LISTED_OVERVIEW = "%1$d API endpoints listed!"
            + " Use the List command to get back the full list!";
    public static final String MESSAGE_INVALID_JSON = "The request was not performed successfully. Check"
            + " that your data is added in the correct JSON format.";
    public static final String MESSAGE_CONNECTION_ERROR = "Connection could not be established. Possible sources of "
            + "error may include but are not limited to:\n"
            + "- Invalid URL\n"
            + "- Server error\n"
            + "- Internet connection\n"
            + "- Invalid command format\n";

    public static final String MESSAGE_CALL_CANCELLED = "The request has been aborted.";
    public static final String MESSAGE_GENERAL_ERROR = "The request was not performed successfully. Possible sources "
            + " of error may include but are not limited to:\n"
            + "- Invalid URL\n"
            + "- Server error\n"
            + "- Internet connection\n"
            + "- Invalid command format\n";
    public static final String MESSAGE_USE_HELP = "Use the help command for more information.";
}
