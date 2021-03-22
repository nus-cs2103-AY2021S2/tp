package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.model.util.predicate.CompositeFieldPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCustomerCommand extends Command {

    public static final String COMMAND_WORD = "findcustomer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all customer given by any of the arguments "
            + "(e.g. names) which contains any of the specified keywords (case-insensitive) and displays them as "
            + "a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME_KEYWORDS...] "
            + "[" + PREFIX_PHONE + "PHONE_KEYWORDS...] "
            + "[" + PREFIX_EMAIL + "EMAIL_KEYWORDS...] "
            + "[" + PREFIX_ADDRESS + "ADDRESS_KEYWORDS...]"
            + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + " alice bob charlie";

    private final CompositeFieldPredicate<Customer> predicate;

    /**
     * Creates a new find command
     * @param predicate predicate of find command
     */
    public FindCustomerCommand(CompositeFieldPredicate<Customer> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(predicate);
        model.setPanelToCustomerList();
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW, model.getFilteredCustomerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCustomerCommand // instanceof handles nulls
                && predicate.equals(((FindCustomerCommand) other).predicate)); // state check
    }

}
