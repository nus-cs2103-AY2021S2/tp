package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.List;
import java.util.function.Predicate;

import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.predicate.LocationContainsPredicate;

/**
 * Clears all items in a specified location.
 */
public class ClearLocationCommand extends ClearCommand {

    public static final String CLEAR_LOCATION_MESSAGE_SUCCESS = "All items in %s "
        + "(if the location exists) are cleared!";


    private final Predicate<Item> predicate;
    private final String location;

    /**
     * Constructor to create a ClearLocationCommand object.
     *
     * @param predicate predicate used to find the items with the given location.
     * @param location string containing the location input given by user.
     */
    public ClearLocationCommand(LocationContainsPredicate predicate, String location) {
        this.location = location;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> currentList = model.getItemList();
        if (currentList.isEmpty()) {
            throw new CommandException(MESSAGE_NO_ITEMS_IN_STOREMANDO);
        }
        model.clearLocation(predicate);
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(String.format(CLEAR_LOCATION_MESSAGE_SUCCESS, location));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ClearLocationCommand // instanceof handles nulls
            && predicate.equals(((ClearLocationCommand) other).predicate)); // state check
    }

}

