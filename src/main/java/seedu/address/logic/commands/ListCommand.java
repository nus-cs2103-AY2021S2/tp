package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.model.Model;
import seedu.address.model.task.TaskDoneStatusPredicate;

/**
 * Lists all tasks or tasks filtered by status in the planner to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_STATUS_TASKS_SUCCESS = "Listed all tasks based on the status you want !!!";

    public static final String MESSAGE_ALL_TASKS_SUCCESS = "Listed all tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List out all tasks in the planner.";

    public static final String INVALID_INPUT = "Invalid input detected !!! The only acceptable formats are list,"
            + "list done and list not done";

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + "\n";

    private final TaskDoneStatusPredicate taskDoneStatus;

    private final boolean isListEverything;

    /**
     * Lists all tasks in the planner based on the status input from the user.
     */
    public ListCommand(TaskDoneStatusPredicate taskDoneStatus) {
        this.taskDoneStatus = taskDoneStatus;
        this.isListEverything = false;
    }

    /**
     * Lists all tasks in the planner to the user.
     */
    public ListCommand(boolean isListEverything) {
        this.isListEverything = isListEverything;
        this.taskDoneStatus = new TaskDoneStatusPredicate("");
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetCalendarDate();

        if (this.isListEverything) {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(MESSAGE_ALL_TASKS_SUCCESS);
        } else {
            model.updateFilteredTaskList(taskDoneStatus);
            return new CommandResult(MESSAGE_STATUS_TASKS_SUCCESS);
        }
    }
}
