package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_SESSION_PLACEHOLDER = "%1$s";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person ID provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_SESSION_DISPLAYED_INDEX = "The session ID provided is invalid";
    public static final String MESSAGE_CANNOT_EDIT = "Please unassign ALL students and tutor before editing the session"
                                                    + "'s day OR timeslot.";
    public static final String MESSAGE_CANNOT_DELETE = "Please unassign ALL students and tutor before deleting the"
                                                        + " session";
}
