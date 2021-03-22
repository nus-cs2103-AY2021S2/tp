package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_UNCOMPLETED_TASKS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ViewUncompletedTasksCommand extends Command {

    public static final String COMMAND_WORD = "viewUncompletedTasks";

    public static final String MESSAGE_SUCCESS = "Listed all Uncompleted Tasks";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_UNCOMPLETED_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
