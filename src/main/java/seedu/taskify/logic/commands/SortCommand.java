package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.taskify.model.Model;

/**
 * Sort all tasks by deadline starting from the earliest date that is not yet done.
 */

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted tasks by deadlines";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortTask();
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
