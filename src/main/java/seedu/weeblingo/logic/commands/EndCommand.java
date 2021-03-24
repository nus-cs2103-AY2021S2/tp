package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Model;

/**
 * Ends the current quiz session and returns the user to the original start page.
 */
public class EndCommand extends Command {

    public static final String COMMAND_WORD = "end";

    public static final String MESSAGE_SUCCESS = "Welcome back.\n"
            + "Enter \"learn\" or \"quiz\" for different modes.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.getMode().getCurrentMode() == 2 || model.getMode().getCurrentMode() == 3) {
            requireNonNull(model);
            model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
            return new CommandResult(MESSAGE_SUCCESS, false, false, false);
        } else {
            throw new CommandException(Messages.MESSAGE_NOT_IN_LEARN_OR_QUIZ_MODE);
        }
    }

}
