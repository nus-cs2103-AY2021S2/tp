package seedu.timeforwheels.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import seedu.timeforwheels.commons.core.Messages;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.NameContainsKeywordsPredicate;

/**
 * Finds and lists all customers in delivery list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class CompletedCommand extends Command {

    public static final String COMMAND_WORD = "completed";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all customers whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    @Override
    public CommandResult execute (Model model) {
        requireNonNull(model);
        String trimmedArgs = "[✓]";
        String[] nameKeywords = trimmedArgs.split("\\s+");
        NameContainsKeywordsPredicate complete = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
        model.updateFilteredCustomerList(complete);
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW, model.getFilteredCustomerList().size()));
    }
}
