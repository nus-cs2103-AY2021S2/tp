package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.AttributeManager;
import seedu.address.model.task.Task;

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

        List<Task> lastShownList = model.getFilteredTaskList();

        int totalTasks = getTotalTasks(lastShownList);
        double percentageOfTasksCompleted = getPercentage(lastShownList);
        int numberDueNextWeek = getNumberDue(lastShownList);

        return new CommandResult(String.format(MESSAGE_SUCCESS, totalTasks, percentageOfTasksCompleted,
                numberDueNextWeek));
    }

    private int getTotalTasks(List<Task> lastShownList) {
        return lastShownList.size();
    }

    private double getPercentage(List<Task> lastShownList) {
        double totalTasks = lastShownList.size();
        double totalDone = 0;

        for (Task t : lastShownList) {

            if (new AttributeManager(t).isDone()) {
                totalDone++;
            }
        }

        double decimalFormat = totalDone / totalTasks;

        return decimalFormat * 100;
    }

    private int getNumberDue(List<Task> lastShownList) {
        int totalNumDue = 0;

        for (Task t : lastShownList) {
            AttributeManager attributeManager = new AttributeManager(t);

            if (!attributeManager.isEmptyDate()) {
                boolean isWithinSevenDays = attributeManager.isWithinSevenDays(LocalDate.now());
                if (isWithinSevenDays) {
                    totalNumDue++;
                }
            }
        }
        return totalNumDue;
    }
}
