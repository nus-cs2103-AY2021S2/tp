package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.ListTaskFormatPredicate;

public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "tlist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tasks by "
            + "day or week and displays them as a list sorted by date.\n"
            + "Parameters: day/week \n"
            + "Example: " + COMMAND_WORD + " week";

    private final ListTaskFormatPredicate predicate;

    public ListTaskCommand(ListTaskFormatPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTaskCommand // instanceof handles nulls
                && predicate.equals(((ListTaskCommand) other).predicate)); // state check
    }
}
