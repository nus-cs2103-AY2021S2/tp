package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.taskify.commons.core.Messages;
import seedu.taskify.commons.core.index.Index;
import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.model.Model;
import seedu.taskify.model.task.Task;

public class DeleteMultipleCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete multiple tasks identified by the index number used in the displayed task list.\n"
            + "Parameters: FIRST_INDEX SECOND_INDEX ...(must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1" + " 3" + " 5";

    //public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Tasks: %1$s";

    private final List<Index> targetIndexes;

    public DeleteMultipleCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        List<Task> tasksToDelete = new ArrayList<>();

        for (int i = 0; i < targetIndexes.size(); i++) {
            Task taskToDelete = lastShownList.get(targetIndexes.get(i).getZeroBased());
            tasksToDelete.add(taskToDelete);
        }

        for (Task toDelete : tasksToDelete) {
            model.deleteTask(toDelete);
        }

        return new CommandResult(generateSuccessMessage(tasksToDelete));
    }

    private String generateSuccessMessage(List<Task> tasksToDelete) {
        StringBuilder sb = new StringBuilder();
        sb.append("Deleted Tasks: ");
        for (Task toDelete : tasksToDelete) {
            sb.append(toDelete);
        }
        return sb.toString();
    }
}
