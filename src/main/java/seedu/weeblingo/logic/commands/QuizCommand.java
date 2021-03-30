package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.model.Model;

/**
 * Enter flashcard quiz mode.
 */
public class QuizCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_SUCCESS = "You are now in quiz mode.\n"
            + "Enter \"start\" to start quiz.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(Model.PREDICATE_SHOW_ALL_FLASHCARDS);
        model.switchModeQuiz();
        return new CommandResult(MESSAGE_SUCCESS, false, false);

    }
}
