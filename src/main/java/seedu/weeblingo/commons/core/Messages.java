package seedu.weeblingo.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX = "The flashcard index provided is invalid.";
    public static final String MESSAGE_TAG_NOT_IN_LEARN_MODE = "Tagging and deletion of tags "
            + "can only be done in learn mode!";
    public static final String MESSAGE_NOT_IN_MENU_MODE = "Please enter \"end\" to return to menu "
            + "before switching between learn, quiz and history modes.";
    public static final String MESSAGE_NOT_IN_QUIZ_SESSION = "You are not currently in a quiz session.";
    public static final String MESSAGE_TAG_NOT_FOUND = "Oops, no flashcards have that tag!";
}
