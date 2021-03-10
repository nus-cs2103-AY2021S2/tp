package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.address.model.Model;

/**
 * Lists all flashcards in the address book to the user.
 */
public class LearnCommand extends Command {

    public static final String COMMAND_WORD = "learn";

    public static final String MESSAGE_SUCCESS = "You are now in learn mode";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }

}
