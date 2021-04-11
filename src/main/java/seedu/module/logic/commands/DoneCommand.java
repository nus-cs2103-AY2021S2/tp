package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.module.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.task.DoneStatus;
import seedu.module.model.task.Task;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Done Task: %1$s";
    public static final String MESSAGE_TASK_ALREADY_DONE = "Task is already done!";

    private final Index index;

    public DoneCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMarkDone = lastShownList.get(index.getZeroBased());
        Task doneTask = createDoneTask(taskToMarkDone);

        if (taskToMarkDone.equals(doneTask) && model.hasTask(doneTask)) {
            throw new CommandException(MESSAGE_TASK_ALREADY_DONE);
        }

        model.setTask(taskToMarkDone, doneTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, doneTask));
    }

    private static Task createDoneTask(Task taskToMarkDone) {
        assert taskToMarkDone != null;

        DoneStatus newDoneStatus = new DoneStatus(true);

        return Task.setDoneStatus(taskToMarkDone, newDoneStatus);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && index.equals(((DoneCommand) other).index)); // state check
    }
}
