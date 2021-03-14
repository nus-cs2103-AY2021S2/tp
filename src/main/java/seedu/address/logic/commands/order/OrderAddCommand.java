package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderStub;

/**
 * Adds a person to the address book.
 */
public class OrderAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [Insert Usage Here]";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the order list";

    private final OrderStub toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public OrderAddCommand(OrderStub order) {
        requireNonNull(order);
        toAdd = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check model has order here throw MESSAGE_DUPLICATE_ORDER

        // Add order to model here
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderAddCommand // instanceof handles nulls
                && toAdd.equals(((OrderAddCommand) other).toAdd));
    }
}
