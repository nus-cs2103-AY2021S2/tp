package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.comparators.DateTimeComparator;
import seedu.address.logic.comparators.ModuleCodeComparator;
import seedu.address.model.Model;
import seedu.address.model.person.SortingFlag;
import seedu.address.model.person.Task;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all tasks according to one of three"
            + "specified parameters: date & time, module code, or priority tag.\n";

    public static final String MESSAGE_SUCCESS = "Tasks have been successfully sorted according to: ";

    private final SortingFlag sortingFlag;
    private final Comparator<Task> sortingComparator;

    public SortCommand(SortingFlag sortingFlag) {
        this.sortingFlag = sortingFlag;
        switch (sortingFlag.getSortingFlagType()) {
        case DATE_TIME:
            sortingComparator = new DateTimeComparator();
            break;
        case MODULE_CODE:
            sortingComparator = new ModuleCodeComparator();
            break;
        // need to add case for priority tag; waiting on weikiat's implementation
        default:
            throw new IllegalStateException("Unexpected value: " + sortingFlag.getSortingFlagType());
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
}
