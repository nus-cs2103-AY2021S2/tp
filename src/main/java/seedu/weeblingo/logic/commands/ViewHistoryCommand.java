package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.model.Model.PREDICATE_SHOW_ALL_SCORES;

import seedu.weeblingo.model.Model;

/**
 * Lists all scoring history in the Flashcard-book to the user.
 */
public class ViewHistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_SUCCESS = "These are your past quiz attempts.\n"
            + "Enter \"end\" to go back to main menu, or "
            + "\"quiz\" to start a new quiz session, or "
            + "\"learn\" to start a learn session on all flashcards.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredScoreHistory(PREDICATE_SHOW_ALL_SCORES);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
    }
}
