package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.taskify.commons.core.Messages;
import seedu.taskify.model.Model;
import seedu.taskify.model.task.predicates.NameContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names contain any of "
                       + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
                       + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
                       + "Example: " + COMMAND_WORD + " cs2103T Tutorial";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (CommandResult.isExpiredTab()) {
            model.updateExpiredFilterTaskList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getExpiredFilteredTaskList().size()));
        } else if (CommandResult.isCompletedTab()) {
            model.updateCompletedFilterTaskList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getCompletedFilteredTaskList().size()));
        } else {
            model.updateFilteredTaskList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));

        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                       || (other instanceof FindCommand // instanceof handles nulls
                                   && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
