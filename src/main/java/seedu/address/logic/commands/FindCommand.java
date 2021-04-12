package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.filters.combinator.FilterCombinator;
import seedu.address.model.Model;

/**
 * Finds and lists all customers in address book whose name contains any of the argument keywords. Keyword matching is
 * case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all customers whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " \' find n/Raj /AND [ p/9876 /OR /NOT b/1998 ] \'";

    public static final String MESSAGE_SYNTAX_ERROR = "There is a syntax error in the given find command : syntax "
        + "should be a well bracketed sequence like \' find n/Raj /AND [ p/9876 /OR /NOT b/1998 ] \'";

    private final FilterCombinator predicate;

    public FindCommand(FilterCombinator predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (!predicate.isValidCombinator()) {
            return new CommandResult(MESSAGE_SYNTAX_ERROR);
        }

        model.updateFilteredCustomerList(predicate);
        if (model.getFilteredCustomerList().size() == 1) {
            return new CommandResult(String.format(Messages.MESSAGE_SINGULAR_CUSTOMER_LISTED_OVERVIEW, 1));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW, model.getFilteredCustomerList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
            && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
