package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.DeadlineBeforeDatePredicate;

import static java.util.Objects.requireNonNull;

public class FindTasksBeforeCommand extends Command {
    public static final String COMMAND_WORD = "findBefore";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all tasks with deadline before the specified date "
            + " and displays them as a list with index numbers.\n "
            + "Parameters: DEADLINE \n"
            + "Example: " + COMMAND_WORD + " 2021-03-26";

    private final DeadlineBeforeDatePredicate predicate;

    public FindTasksBeforeCommand(DeadlineBeforeDatePredicate predicate) {
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
                || (other instanceof FindTasksBeforeCommand // instanceof handles nulls
                && predicate.equals(((FindTasksBeforeCommand) other).predicate)); // state check
    }
}
