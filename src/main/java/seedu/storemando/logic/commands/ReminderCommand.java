package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.storemando.commons.core.Messages;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.ItemExpiringPredicate;

public class ReminderCommand extends Command {

    public static final String COMMAND_WORD = "reminder";

    private final ItemExpiringPredicate predicate;

    public ReminderCommand (ItemExpiringPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_NUMBER_OF_ITEMS_LISTED_OVERVIEW, model.getFilteredItemList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ReminderCommand // instanceof handles nulls
            && predicate.equals(((ReminderCommand) other).predicate)); // state check
    }
}
