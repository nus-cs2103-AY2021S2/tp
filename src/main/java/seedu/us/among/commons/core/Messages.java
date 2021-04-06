package seedu.us.among.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_COMMAND_ERROR = "Error: %1$s\n%2$s";
    public static final String MESSAGE_INDEX_NOT_WITHIN_LIST =
             "Index provided is not within the saved endpoint list.\n";
    public static final String MESSAGE_INVALID_INDEX = "Either no index has been provided or index provided is not a non-zero unsigned integer within the "
            + "allowed range.\n";
    public static final String MESSAGE_ENDPOINTS_LISTED_OVERVIEW = "%1$d API endpoints listed!";
    public static final String MESSAGE_INVALID_JSON = "The request was not performed successfully. Check"
            + " that your data is added in the correct JSON format.";
    public static final String MESSAGE_CONNECTION_ERROR = "Connection was lost or could not be established."
            + " Check your internet connection and endpoint URL. This could also be a problem with the server"
            + " you are attempting to connect to.";
    public static final String MESSAGE_CALL_CANCELLED = "The request has been aborted.";
    public static final String MESSAGE_GENERAL_ERROR = "The request was not performed successfully."
            + " Check that your endpoint fields are correct. This could also be a problem with the server"
            + " you are attempting to connect to.";
}
