package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static final String MESSAGE_INVALID_INDEX_PREAMBLE = "The provided INDEX is invalid. ";

    public static final String MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX =
            MESSAGE_INVALID_INDEX_PREAMBLE + "The Resident INDEX must be between 1 and %s (inclusive)";
    public static final String MESSAGE_NO_RESIDENTS =
            "There are no Residents in your current view or no Residents in SunRez yet";
    public static final String MESSAGE_RESIDENTS_LISTED_OVERVIEW = "%1$d residents listed!";

    public static final String MESSAGE_INVALID_ROOM_DISPLAYED_INDEX =
            MESSAGE_INVALID_INDEX_PREAMBLE + "The Room INDEX must be between 1 and %s (inclusive)";
    public static final String MESSAGE_NO_ROOMS = "There are no Rooms in your current view or no Rooms in SunRez yet";
    public static final String MESSAGE_ROOMS_LISTED_OVERVIEW = "%1$d rooms listed!";

    public static final String MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX =
            MESSAGE_INVALID_INDEX_PREAMBLE + "The Issue INDEX must be between 1 and %s (inclusive)";
    public static final String MESSAGE_NO_ISSUES =
            "There are no Issues in your current view or no Issues in SunRez yet";
    public static final String MESSAGE_ISSUES_LISTED_OVERVIEW = "%1$d issues listed!";

}
