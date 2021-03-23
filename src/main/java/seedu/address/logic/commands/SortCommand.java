package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Sorts the meetings using the specified comparator.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the task in the list."
            + "\n\nParameters: "
            + "\n" + "sort by a"
            + "\n" + "sort by d";

    public static final String MESSAGE_SUCCESS = "Successfully sorted meeting";

    public static final String MESSAGE_SORT_TYPE_INVALID = "Invalid Sort Type";

    private final Comparator<Task> comparator;

    /**
     * Constructor for SortMeetingCommand. Checks the order of sort required
     * and producers the appropriate comparator.
     */
    public SortCommand(Comparator<Task> comparator, Boolean isAscending) {
        assert (comparator != null);
        if (!isAscending) {
            this.comparator = comparator.reversed();
        } else {
            this.comparator = comparator;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedTaskList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

