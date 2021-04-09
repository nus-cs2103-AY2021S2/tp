package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.iscam.commons.core.Messages;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.PlanContainsKeywordsPredicate;

/**
 * Finds and lists all clients in iscam book whose insurance plan contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPlansCommand extends Command {
    public static final String COMMAND_WORD = "findplan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all clients whose insurance plan contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " MediShield Life";

    private final PlanContainsKeywordsPredicate predicate;

    public FindPlansCommand(PlanContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW, model.getFilteredClientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPlansCommand // instanceof handles nulls
                && predicate.equals(((FindPlansCommand) other).predicate)); // state check
    }
}
