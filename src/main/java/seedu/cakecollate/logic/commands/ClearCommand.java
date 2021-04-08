package seedu.cakecollate.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.cakecollate.model.CakeCollate;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.OrderItems;

/**
 * Clears the orders and the order items in cakecollate.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "CakeCollate has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all entries from CakeCollate.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setCakeCollate(new CakeCollate());
        model.setOrderItems(new OrderItems());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
