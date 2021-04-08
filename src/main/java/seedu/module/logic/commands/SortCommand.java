package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.module.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.module.logic.parser.CliSyntax.PREFIX_WORKLOAD;

import java.util.Comparator;

import seedu.module.model.Model;
import seedu.module.model.task.Task;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts task by one factor. \n"
        + "Parameters: "
        + PREFIX_TASK_NAME + " or "
        + PREFIX_DESCRIPTION + " or "
        + PREFIX_DEADLINE + " or "
        + PREFIX_WORKLOAD + " or "
        + PREFIX_TAG + " or "
        + PREFIX_MODULE + "\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_WORKLOAD + " ";

    private final String messageSuccessByFactor;
    private final Comparator<Task> factor;

    /**
     * Creates a SortCommand to sort the tasks by {@code Comparator<Task>}
     * @param factor the factor used to sort the tasks.
     */
    public SortCommand(Comparator<Task> factor) {
        this.factor = factor;
        this.messageSuccessByFactor = "Sorted all tasks by " + factor.toString() + ".";
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortTasks(factor);
        return new CommandResult(messageSuccessByFactor);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        } else {
            boolean isSortedByWorkload = this.factor instanceof Task.WorkloadComparator
                && ((SortCommand) other).factor instanceof Task.WorkloadComparator;
            boolean isSortedByDeadline = this.factor instanceof Task.DeadlineComparator
                && ((SortCommand) other).factor instanceof Task.DeadlineComparator;
            boolean isSortedByTaskName = this.factor instanceof Task.NameComparator
                && ((SortCommand) other).factor instanceof Task.NameComparator;
            boolean isSortedByModule = this.factor instanceof Task.ModuleComparator
                && ((SortCommand) other).factor instanceof Task.ModuleComparator;
            boolean isSortedByDescription = this.factor instanceof Task.DescriptionComparator
                && ((SortCommand) other).factor instanceof Task.DescriptionComparator;
            boolean isSortedByNumberOfTag = this.factor instanceof Task.TagComparator
                && ((SortCommand) other).factor instanceof Task.TagComparator;
            return isSortedByDeadline || isSortedByDescription || isSortedByModule || isSortedByNumberOfTag
                || isSortedByTaskName || isSortedByWorkload;
        }
    }
}
