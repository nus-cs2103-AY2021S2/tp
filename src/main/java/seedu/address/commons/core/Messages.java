package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ENTRY_INDEX = "The entry index provided is invalid";
    public static final String MESSAGE_ENTRY_END_DATE_IN_PAST = "The provided end date and time is in the past";
    public static final String MESSAGE_DELETE_ENTRY_SUCCESS = "Deleted Entry: %1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX = "The contact index provided is invalid";
    public static final String MESSAGE_ENTRY_START_DATE_IN_PAST = "Time travel is not allowed! Please provide a valid"
            + " date time!";
    public static final String MESSAGE_INVALID_DATE_RANGE = "The start date/time must be strictly before"
            + " the end date/time!";
    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "%1$d contacts listed!";
    public static final String MESSAGE_ENTRIES_LISTED_OVERVIEW = "%1$d entries listed!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_OVERLAPPING_ENTRY = "This entry has dates that overlap with other"
            + " existing entries!";
    public static final String MESSAGE_EDIT_CONTACT_SUCCESS = "Edited Contact: %1$s";
    public static final String MESSAGE_DUPLICATE_CONTACT = "The contact provided already exists in the address book.";
    public static final String MESSAGE_CONTACT_NAME_DOES_NOT_EXIST = "The contact name provided does not exist!";
    public static final String MESSAGE_EDIT_ENTRY_SUCCESS = "Edited entry: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_FREE = "You're free!";
    public static final String MESSAGE_NOT_FREE = "Sorry, you're not free. Schedules occupying that time interval "
            + "listed below!";
    public static final String MESSAGE_DUPLICATED_ENTRY = "This entry already exists in the list.";

}
