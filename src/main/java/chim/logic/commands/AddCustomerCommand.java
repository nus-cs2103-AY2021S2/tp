package chim.logic.commands;

import static chim.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static chim.logic.parser.CliSyntax.PREFIX_EMAIL;
import static chim.logic.parser.CliSyntax.PREFIX_NAME;
import static chim.logic.parser.CliSyntax.PREFIX_PHONE;
import static chim.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import chim.logic.commands.exceptions.CommandException;
import chim.model.Model;
import chim.model.customer.Customer;

/**
 * Adds a customer to CHIM.
 */
public class AddCustomerCommand extends AddCommand {

    public static final String COMMAND_WORD = "addcustomer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a customer to CHIM.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New customer added: %1$s";
    public static final String MESSAGE_DUPLICATE_CUSTOMER = "Customer with that phone number already exists in CHIM.";

    private final Customer toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Customer}
     */
    public AddCustomerCommand(Customer customer) {
        requireNonNull(customer);
        toAdd = customer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCustomer(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CUSTOMER);
        }

        model.addCustomer(toAdd);
        model.setPanelToCustomerList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    public Customer getToAdd() {
        return toAdd;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCustomerCommand // instanceof handles nulls
                && toAdd.equals(((AddCustomerCommand) other).toAdd));
    }
}
