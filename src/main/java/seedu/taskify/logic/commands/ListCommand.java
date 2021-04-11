package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.model.Model;

/**
 * Lists all tasks in the taskify to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    public static final String MESSAGE_SWITCH_TO_HOME = "Switch back to home page to list!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!CommandResult.isHomeTab()) {
            throw new CommandException(MESSAGE_SWITCH_TO_HOME);
        }

        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
