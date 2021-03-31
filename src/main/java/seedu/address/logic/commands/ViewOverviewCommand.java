package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ShowOverviewTabUiCommand;
import seedu.address.model.Model;

/**
 * Show overview of a Project.
 */
public class ViewOverviewCommand extends Command {

    public static final String COMMAND_WORD = "overview";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the overview of an open project.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Viewing Overview";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS, new ShowOverviewTabUiCommand()).setIgnoreHistory(true);
    }
}
