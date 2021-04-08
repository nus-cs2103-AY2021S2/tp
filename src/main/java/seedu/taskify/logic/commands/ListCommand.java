package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.taskify.model.Model;

/**
 * Lists all tasks in the taskify to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";
    public static final String MESSAGE_SUCCESS_EXPIRED = "Listed all expired tasks";
    public static final String MESSAGE_SUCCESS_COMPLETED = "Listed all completed tasks";
    public static final String MESSAGE_SUCCESS_UNCOMPLETED = "Listed all uncompleted tasks";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (CommandResult.isHomeTab()) {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(MESSAGE_SUCCESS);
        } else if (CommandResult.isExpiredTab()) {
            model.updateExpiredFilterTaskList(PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(MESSAGE_SUCCESS_EXPIRED);
        } else if (CommandResult.isCompletedTab()) {
            model.updateCompletedFilterTaskList(PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(MESSAGE_SUCCESS_COMPLETED);
        } else {
            model.updateCompletedFilterTaskList(PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(MESSAGE_SUCCESS_UNCOMPLETED);
        }
    }
}
