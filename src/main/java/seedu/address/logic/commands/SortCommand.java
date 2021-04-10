package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.comparators.DateTimeComparator;
import seedu.address.logic.comparators.ModuleCodeComparator;
import seedu.address.logic.comparators.PriorityTagComparator;
import seedu.address.logic.comparators.TaskNameComparator;
import seedu.address.logic.comparators.WeightageComparator;
import seedu.address.logic.util.SortingFlag;
import seedu.address.model.Model;
import seedu.address.model.person.Task;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all tasks according to one of five"
            + " specified parameters: date & time, task name, module code, priority tag, or weightage.\n";

    public static final String MESSAGE_SUCCESS = "Tasks have been successfully sorted according to: ";

    private final SortingFlag sortingFlag;
    private final Comparator<Task> sortingComparator;

    /**
     * Creates a SortCommand with the appropriate sorting flag and comparator.
     * @param sortingFlag a valid sorting flag
     * @throws IllegalStateException if the sorting flag provided is not valid.
     */
    public SortCommand(SortingFlag sortingFlag) {
        this.sortingFlag = sortingFlag;
        switch (sortingFlag.getSortingType()) {
        case DATE_TIME:
            sortingComparator = new DateTimeComparator();
            break;
        case TASK_NAME:
            sortingComparator = new TaskNameComparator();
            break;
        case MODULE_CODE:
            sortingComparator = new ModuleCodeComparator();
            break;
        case PRIORITY_TAG:
            sortingComparator = new PriorityTagComparator();
            break;
        case WEIGHTAGE:
            sortingComparator = new WeightageComparator();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + sortingFlag.getSortingType());
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortTasks(sortingComparator);
        return new CommandResult(MESSAGE_SUCCESS + sortingFlag.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand) // instanceof handles null
                && sortingFlag.equals(((SortCommand) other).sortingFlag); // state check
    }

    @Override
    public String toString() {
        return "SORT";
    }
}
