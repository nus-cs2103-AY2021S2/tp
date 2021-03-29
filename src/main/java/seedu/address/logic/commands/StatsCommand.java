package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Shows statistics of the planner.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the total number of tasks"
            + "in the planner, percentage of tasks completed, and the number of tasks happening/due in the "
            + "next 7 days.\n\n";

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + "\n";

    public static final String MESSAGE_SUCCESS = "Total number of tasks: %1$s\n"
            + "Percentage of tasks completed: %2$.2f%%\n"
            + "Number of tasks due in the next 7 days: %3$s\n";

    public static final String MESSAGE_NO_TASKS = "There are no tasks in the planner to obtain statistics from.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isEmpty()) {
            throw new CommandException(MESSAGE_NO_TASKS);
        }

        int totalTasks = getTotalTasks(model);
        double percentageOfTasksCompleted = getPercentage(model);
        int numberDueNextWeek = getNumberDue(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS, totalTasks, percentageOfTasksCompleted,
                numberDueNextWeek));
    }

    private int getTotalTasks(Model model) {
        return model.size();
    }

    private double getPercentage(Model model) {
        return model.getPercentage();
    }

    private int getNumberDue(Model model) {
        return model.getNumberDue();
    }
}
