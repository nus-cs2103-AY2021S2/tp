package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.weeblingo.model.Model;

/**
 * Lists all flashcards in the address book to the user.
 */
public class LearnCommand extends Command {

    public static final String COMMAND_WORD = "learn";

    public static final String MESSAGE_SUCCESS = "You are now in learn mode.\n"
            + "Enter \"end\" to end your study or "
            + "\"quiz\" to start a quiz session on the flashcards.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        model.getMode().switchModeLearn();
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, true);
    }
}
