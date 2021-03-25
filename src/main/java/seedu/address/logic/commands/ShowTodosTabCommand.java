package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ShowTodosTabUiCommand;
import seedu.address.model.Model;

/**
 * Show todos tab of a Project.
 */
public class ShowTodosTabCommand extends Command {

    public static final String COMMAND_WORD = "tabT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the todos tab of an open project.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Viewing Todos Tab";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS,
                new ShowTodosTabUiCommand());
    }
}
