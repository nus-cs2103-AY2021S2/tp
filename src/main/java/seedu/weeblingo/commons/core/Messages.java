package seedu.weeblingo.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX = "The flashcard index provided is invalid";
    public static final String MESSAGE_FLASHCARDS_LISTED_OVERVIEW = "%1$d flashcards listed!";
    public static final String NO_QUIZ_ERROR_MESSAGE = "The quiz hasn't started yet!";
    public static final String MESSAGE_END_IN_MENU = "Invalid 'end' command in menu mode";
    public static final String MESSAGE_NOT_IN_QUIZ_MODE = "Not in quiz mode yet.";
    public static final String QUIZ_END_MESSAGE = "The Quiz is over! \n"
            + "Enter \"end\" to end the quiz. \n";
    public static final String MESSAGE_TAG_NOT_IN_LEARN_MODE = "Tagging and deletion of tags "
            + "can only be done in learn mode!";

    public static final String MULTIPLE_CHECKING_AFTER_SUCCESS = "You already got it correct, please enter \"next\" "
            + "to go to the next question";
}
