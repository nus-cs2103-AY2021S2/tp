package chim.logic.commands;

import static chim.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static chim.logic.parser.CliSyntax.PREFIX_EMAIL;
import static chim.logic.parser.CliSyntax.PREFIX_NAME;
import static chim.logic.parser.CliSyntax.PREFIX_PHONE;
import static java.util.Objects.requireNonNull;

import chim.commons.core.Messages;
import chim.model.Model;
import chim.model.customer.Customer;
import chim.model.util.predicate.FieldPredicate;

/**
 * Finds the customers in CHIM whose details matches the input keywords; keyword matching is case insensitive.
 * Lists the matching customers to the user.
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

    private final FieldPredicate<Customer> predicate;

    /**
     * Creates a new find command
     * @param predicate predicate of find command
     */
    public FindCustomerCommand(FieldPredicate<Customer> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(predicate);
        model.setPanelToCustomerList();

        String message = String.format(
                Messages.MESSAGE_CUSTOMERS_FOUND_OVERVIEW,
                model.getFilteredCustomerList().size(),
                predicate);

        if (model.getFilteredCustomerList().size() == 0) {
            message = String.format(Messages.MESSAGE_CUSTOMERS_NOT_FOUND_OVERVIEW, predicate);
        }

        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCustomerCommand // instanceof handles nulls
                && predicate.equals(((FindCustomerCommand) other).predicate)); // state check
    }

}
