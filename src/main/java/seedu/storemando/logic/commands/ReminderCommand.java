package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.expirydate.ItemExpiringPredicate;
import seedu.storemando.model.item.ItemComparatorByExpiryDate;

/**
 * Finds and lists all items in storemando whose item's expiry date is within a certain days/weeks from today.
 */
public class ReminderCommand extends Command {

    public static final String COMMAND_WORD = "reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all items whose expiry date is within "
        + "the user-specified number of days/weeks from the current date. \n"
        + "Parameters: Days/Weeks (must be an integer) [TimeUnitKeyWord] (days/weeks (day/week accepted for -1/0/1)\n"
        + "Example: \n- " + COMMAND_WORD + " 3 days\n- " + COMMAND_WORD + " 1 week";

    public static final String MESSAGE_SUCCESS = "Display all expiring items";

    private final ItemExpiringPredicate predicate;

    public ReminderCommand(ItemExpiringPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateCurrentPredicate(predicate);
        model.updateFilteredItemList(model.getCurrentPredicate());
        ItemComparatorByExpiryDate comparator = new ItemComparatorByExpiryDate();
        model.updateSortedItemList(comparator);
        model.setItems(model.getSortedItemList());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ReminderCommand // instanceof handles nulls
            && predicate.equals(((ReminderCommand) other).predicate)); // state check
    }
}
