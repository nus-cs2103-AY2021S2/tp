package seedu.flashback.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashback.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.flashback.model.Model;

/**
 * Lists all flashcards in the FlashBack to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all flashcards";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
