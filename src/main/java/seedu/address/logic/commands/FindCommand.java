package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.customer.NameContainsKeywordsComparator;
import seedu.address.model.customer.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "[KEYWORDS...]] "
            + "[" + PREFIX_PHONE + "[PHONE...] "
            + "[" + PREFIX_EMAIL + "[KEYWORDS...] "
            + "[" + PREFIX_ADDRESS + "[KEYWORDS...]"
            + "\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;
    private final NameContainsKeywordsComparator comparator;

    /**
     * Creates a new find command
     * @param predicate predicate of find command
     * @param comparator comparator to sort display list
     */
    public FindCommand(NameContainsKeywordsPredicate predicate, NameContainsKeywordsComparator comparator) {
        this.predicate = predicate;
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(predicate);
        model.updateSortedCustomerList(comparator);
        model.setPanelToCustomerList();
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW, model.getFilteredCustomerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }

}
