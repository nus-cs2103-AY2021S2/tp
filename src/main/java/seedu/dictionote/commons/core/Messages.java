package seedu.dictionote.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE = "Command disabled on edit mode!\n"
        + "Type `save` or `quit` to exit edit mode";

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command, "
        + "type 'listcommand' to show list of available command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_NOTE_FORMAT = "Invalid note format! \n%1$s";
    public static final String MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX = "The contact index provided is invalid";
    public static final String MESSAGE_INVALID_NOTE_DISPLAYED_INDEX = "The note index provided is invalid";
    public static final String MESSAGE_INVALID_DICTIONARY_CONTENT_DISPLAYED_INDEX = "The dictionary"
        + "content index provided is invalid";
    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "%1$d contacts listed!";
    public static final String MESSAGE_CONTENTS_LISTED_OVERVIEW = "%1$d relevant content listed!";
    public static final String MESSAGE_DEFINITIONS_LISTED_OVERVIEW = "%1$d relevant definition found!";

    public static final String STANDARD_DATE_TIME_DISPLAY_FORMAT = "dd/MM/yyyy HH:mm:ss";

}
