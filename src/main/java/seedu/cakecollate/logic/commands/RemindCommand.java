package seedu.cakecollate.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.order.ReminderDatePredicate;

/**
 * Finds and lists all orders in CakeCollate database whose delivery date is within X days of the current date.
 *
 */

public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all orders whose delivery dates "
            + "are within X days from the current date, as a list with index numbers.\n"
            + "Parameters: DAYS (0 or more!) \n"
            + "Example: " + COMMAND_WORD + " 3";

    private final ReminderDatePredicate predicate;

    /**
     * @param predicate of the number of days from the current date, to return true for existing orders
     * which delivery dates fall within the date range.
     */
    public RemindCommand(ReminderDatePredicate predicate) {
        assert predicate != null;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_REMINDER_OVERVIEW, model.getFilteredOrderList().size(),
                        predicate.getDays()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemindCommand // instanceof handles nulls
                && predicate.equals(((RemindCommand) other).predicate)); // state check
    }
}
