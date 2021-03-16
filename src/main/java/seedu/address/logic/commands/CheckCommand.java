package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.address.model.Model;

/**
 * Reveals answer for current quiz
 */
public class CheckCommand extends Command {

    public static final String COMMAND_WORD = "check";

    public static final String MESSAGE_SUCCESS = "Answer to the question is shown.\n"
            + "Enter \"end\" to end the quiz "
            + "and \"next\" to move to the next question.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
