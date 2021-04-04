package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.model.Model.PREDICATE_SHOW_ALL_SCORES;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;

/**
 * Lists all scoring history in the Flashcard-book to the user.
 */
public class ViewHistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_SUCCESS = "You are now in history mode. You can view your past attempts.\n"
            + "Enter \"end\" to go back to main menu.";

    public static final String MESSAGE_ALREADY_IN_HISTORY_MODE = "You are already in history mode.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int currentMode = model.getCurrentMode();

        if (currentMode == Mode.MODE_HISTORY) {
            throw new CommandException(MESSAGE_ALREADY_IN_HISTORY_MODE);
        }

        if (currentMode != Mode.MODE_MENU) {
            throw new CommandException(Messages.MESSAGE_NOT_IN_MENU_MODE);
        }

        model.updateFilteredScoreHistory(PREDICATE_SHOW_ALL_SCORES);
        model.switchModeHistory();
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
