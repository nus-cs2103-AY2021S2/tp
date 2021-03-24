package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.Model;

/**
 * Show Today panel.
 */
public class ShowTodayCommand extends Command {

    public static final String COMMAND_WORD = "today";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the today panel.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Viewing Today Panel";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS,
                new ShowTodayUiCommand());
    }
}
