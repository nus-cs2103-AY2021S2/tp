package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.model.Model;

/**
 * Sort all tasks by deadline starting from the earliest date that is not yet done.
 */

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted tasks by their deadlines!";
    public static final String MESSAGE_SWITCH_TO_HOME = "Switch back to home page to sort!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!CommandResult.isHomeTab()) {
            throw new CommandException(MESSAGE_SWITCH_TO_HOME);
        }
        requireNonNull(model);
        model.sortTask();
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand); // instanceof handles nulls
    }
}
