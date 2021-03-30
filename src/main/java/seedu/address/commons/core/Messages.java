package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX =
            "One of the passenger indexes provided is invalid";
    public static final String MESSAGE_PASSENGER_LISTED_OVERVIEW = "%1$d passengers listed!";
    public static final String MESSAGE_TRIPS_LISTED_OVERVIEW = "%1$d trips listed!";

    /**
     * Prevents Messages from being instantiated.
     */
    private Messages() {}

}
