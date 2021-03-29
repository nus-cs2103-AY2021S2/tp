package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_UNDONE_TASKS;

import seedu.address.model.Model;
//import seedu.address.model.task.Task;
//import seedu.address.model.task.predicates.TaskDoneStatusPredicate;

//import java.util.function.Predicate;

/**
 * Lists all tasks or tasks filtered by status in the planner to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "ls";

    public static final String MESSAGE_STATUS_TASKS_SUCCESS = "Listed all tasks based specified status.";

    public static final String MESSAGE_ALL_TASKS_SUCCESS = "Listed all tasks.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists out all tasks in the planner.\n\n"
            + "Parameters: "
            + "[done]/[not done]";

    public static final String INVALID_INPUT = "Invalid input detected! The only acceptable formats are ls,"
            + "ls done and ls not done";

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + "[done]/[not done]\n";

    private final boolean isListEverything;

    /**
     * Lists all tasks in the planner based on the status input from the user.
     */
    public ListCommand(boolean isListEverything) {
        this.isListEverything = isListEverything;
    }

    public boolean getListEverything() {
        return this.isListEverything;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetCalendarDate();

        if (this.isListEverything) {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(MESSAGE_ALL_TASKS_SUCCESS);
        } else {
            model.updateFilteredTaskList(PREDICATE_SHOW_UNDONE_TASKS);
            return new CommandResult(MESSAGE_STATUS_TASKS_SUCCESS);
        }
    }
}
