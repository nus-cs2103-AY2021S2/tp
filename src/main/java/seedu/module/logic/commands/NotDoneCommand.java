package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.module.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Set;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Description;
import seedu.module.model.task.DoneStatus;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Task;
import seedu.module.model.task.Time;
import seedu.module.model.task.Workload;

public class NotDoneCommand extends Command {
    public static final String COMMAND_WORD = "notdone";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as not done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_NOT_DONE_TASK_SUCCESS = "Not done Task: %1$s";
    public static final String MESSAGE_TASK_ALREADY_NOT_DONE = "Task is already not done!";

    private final Index index;

    public NotDoneCommand(Index index) {
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
        Task doneTask = createNotDoneTask(taskToMarkDone);

        if (taskToMarkDone.equals(doneTask) && model.hasTask(doneTask)) {
            throw new CommandException(MESSAGE_TASK_ALREADY_NOT_DONE);
        }

        model.setTask(taskToMarkDone, doneTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_NOT_DONE_TASK_SUCCESS, doneTask));
    }

    private static Task createNotDoneTask(Task taskToMarkDone) {
        assert taskToMarkDone != null;

        Name name = taskToMarkDone.getName();
        Time startTime = taskToMarkDone.getStartTime();
        Time deadline = taskToMarkDone.getDeadline();
        Module module = taskToMarkDone.getModule();
        Description description = taskToMarkDone.getDescription();
        Workload workload = taskToMarkDone.getWorkload();
        DoneStatus newDoneStatus = new DoneStatus(false);
        Set<Tag> tags = taskToMarkDone.getTags();

        if (taskToMarkDone.isDeadline()) {
            return new Task(name, deadline, module, description, workload, newDoneStatus, tags);
        } else {
            return new Task(name, startTime, deadline, module, description, workload, newDoneStatus, tags);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NotDoneCommand // instanceof handles nulls
                && index.equals(((NotDoneCommand) other).index)); // state check
    }
}
