package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * Deletes a task identified using its description
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "tdelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the description used in the task list.\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " exam";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final TaskDescription targetTaskDescription;


    public DeleteTaskCommand(TaskDescription targetTaskDescription) {
        this.targetTaskDescription = targetTaskDescription;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();
        boolean taskDeleted = false;
        for (Task task : lastShownTaskList) {
            if (targetTaskDescription.toString().equals(task.getTaskDescription().toString())) {
                model.deleteTask(task);
                taskDeleted = true;
                break;
            }
        }
        if (taskDeleted) {
            return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, targetTaskDescription));
        } else {
            throw new CommandException(Messages.MESSAGE_TASK_DOES_NOT_EXIST);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteTaskCommand
                && targetTaskDescription.equals(((DeleteTaskCommand) other).targetTaskDescription));
    }
}
