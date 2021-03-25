package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ShowOverviewTabUiCommand;
import seedu.address.model.Model;

/**
 * Show overview tab of a Project.
 */
public class ShowOverviewTabCommand extends Command {

    public static final String COMMAND_WORD = "tabO";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the overview tab of an open project.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Viewing Overview Tab";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS,
                new ShowOverviewTabUiCommand());
    }
}
