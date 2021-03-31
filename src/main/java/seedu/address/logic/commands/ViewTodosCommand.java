package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ShowTodosTabUiCommand;
import seedu.address.model.Model;

/**
 * Show all todos of a Project.
 */
public class ViewTodosCommand extends Command {

    public static final String COMMAND_WORD = "todos";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the todos of an open project.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Viewing Todos";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS, new ShowTodosTabUiCommand()).setIgnoreHistory(true);
    }
}
