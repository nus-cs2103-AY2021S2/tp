package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Sorts the meetings using the specified comparator.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the task in the planner.\n\n"
            + "Parameters: "
            + "by a/ by d";

    public static final String MESSAGE_SUCCESS = "Successfully sorted.";

    public static final String MESSAGE_SORT_TYPE_INVALID = "Invalid sort type";

    private final Comparator<Task> comparator;

    private boolean isAscending;
    /**
     * Constructor for SortMeetingCommand. Checks the order of sort required
     * and producers the appropriate comparator.
     */
    public SortCommand(Comparator<Task> comparator, Boolean isAscending) {
        assert (comparator != null);
        if (!isAscending) {
            this.isAscending = false;
            this.comparator = comparator.reversed();
        } else {
            this.isAscending = true;
            this.comparator = comparator;
        }
    }

    @Override
    public boolean equals(Object o) {
        SortCommand that = (SortCommand) o;
        return this.isAscending == that.isAscending;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comparator);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedTaskList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

