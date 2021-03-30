package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.TaskContainsKeywordPredicate;

/**
 * Finds and lists all tasks in HeyMatez with its title or description containing any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTasksCommand extends Command {
    public static final String COMMAND_WORD = "findTasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks with its title or description "
            + "containing the specified keywords (case-insensitive) and displays them as a list. \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " plan meeting proposal";

    private final TaskContainsKeywordPredicate predicate;

    public FindTasksCommand(TaskContainsKeywordPredicate predicate) {
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
                || (other instanceof FindTasksCommand // instanceof handles nulls
                && predicate.equals(((FindTasksCommand) other).predicate)); // state check
    }
}
