package seedu.timeforwheels.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.timeforwheels.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.timeforwheels.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.timeforwheels.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.timeforwheels.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.timeforwheels.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.timeforwheels.logic.commands.exceptions.CommandException;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.Customer;


/**
 * Adds a customer to the delivery list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a customer to the delivery list. "
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
            + PREFIX_TAG + "regular "
            + PREFIX_TAG + "fragile";

    public static final String MESSAGE_SUCCESS = "The following new customer has been added: %1$s";
    public static final String MESSAGE_DUPLICATE_CUSTOMER = "This customer already exists in the delivery list";

    private final Customer toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Customer}
     */
    public AddCommand(Customer customer) {
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
