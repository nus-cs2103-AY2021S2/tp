package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderStub;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class OrderDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [Insert Usage Here]";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order: %1$s";

    private final Index targetIndex;

    public OrderDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        OrderStub orderToDelete = new OrderStub();

        // Delete order here from model
        return new CommandResult(String.format(MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((OrderDeleteCommand) other).targetIndex)); // state check
    }
}
