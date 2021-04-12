package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.storemando.commons.core.Messages;
import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.expirydate.predicate.ItemExpiringPredicate;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.comparator.ItemComparatorByExpiryDate;

/**
 * Finds and lists all items in storemando whose item's expiry date is within a certain days/weeks from today.
 */
public class ReminderCommand extends Command {

    public static final String COMMAND_WORD = "reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all items whose expiry date is within "
        + "the user-specified number of days/weeks from the current date. \n"
        + "Parameters: NUMBER (must be an integer) TIMEUNITKEYWORD (days/weeks (day/week accepted for -1/0/1))\n"
        + "Example: \n1. " + COMMAND_WORD + " 3 days\n2. " + COMMAND_WORD + " 1 week";

    public static final String MESSAGE_SUCCESS_EXPIRING_ITEM = "Filtered all items that are expiring within %d %s or has"
        + " already expired.";
    public static final String MESSAGE_SUCCESS_EXPIRED_ITEM = "Filtered all items that has been expired for at least"
        + " %d %s.";
    public static final String MESSAGE_SUCCESS_EXPIRING_TODAY_ITEM = "Filtered all items that are expiring today or "
        + "has already expired.";

    public static final String MESSAGE_INCORRECT_INTEGER = "Number provided must be greater than -366 "
        + "and cannot exceed 365.";

    private final ItemExpiringPredicate predicate;
    private final long numOfDaysOrWeeksFromToday;
    private final String timeUnit;

    /**
     * Constructor for reminder command with ItemExpiringPredicate predicate, numOfDaysOrWeeksFromToday and the timeUnit
     * specified.
     * @param predicate The predicate that will be use to filter the item.
     * @param numOfDaysOrWeeksFromToday The number of days away from today.
     * @param timeUnit The unit of time specified by user. It is either in day(s) or week(s).
     */
    public ReminderCommand(ItemExpiringPredicate predicate, long numOfDaysOrWeeksFromToday, String timeUnit) {
        this.predicate = predicate;
        this.numOfDaysOrWeeksFromToday = numOfDaysOrWeeksFromToday;
        this.timeUnit = timeUnit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String message = getMessage();
        List<Item> currentList = model.getFilteredItemList();
        if (currentList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_NO_ITEM_IN_LIST);
        }
        model.updateCurrentPredicate(predicate);
        model.updateFilteredItemList(model.getCurrentPredicate());
        ItemComparatorByExpiryDate comparator = new ItemComparatorByExpiryDate();
        model.updateSortedItemList(comparator);
        model.setItems(model.getSortedItemList());
        return new CommandResult(message);
    }

    public String getMessage() {
        if (this.numOfDaysOrWeeksFromToday < 0) {
            return String.format(MESSAGE_SUCCESS_EXPIRED_ITEM, Math.abs(numOfDaysOrWeeksFromToday), timeUnit);
        } else if (this.numOfDaysOrWeeksFromToday == 0) {
            return MESSAGE_SUCCESS_EXPIRING_TODAY_ITEM;
        } else {
            return String.format(MESSAGE_SUCCESS_EXPIRING_ITEM, numOfDaysOrWeeksFromToday, timeUnit);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ReminderCommand // instanceof handles nulls
            && predicate.equals(((ReminderCommand) other).predicate)); // state check
    }
}
