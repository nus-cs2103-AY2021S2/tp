package seedu.cakecollate.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.cakecollate.commons.core.Messages.MESSAGE_ORDERS_LISTED_OVERVIEW;
import static seedu.cakecollate.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import seedu.cakecollate.model.Model;

/**
 * Lists all orders in the cakecollate to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all orders.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of all orders from CakeCollate.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()));
    }
}
