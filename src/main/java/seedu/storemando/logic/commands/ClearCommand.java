package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.storemando.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.List;
import java.util.function.Predicate;

import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;

/**
 * Clears the storemando or a specified location.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All items in the specified location (if any) are cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clear items in storemando or a specified location. "
        + "Parameters: "
        + "[" + PREFIX_LOCATION + "LOCATION]\n"
        + "Example:\n"
        + "1. " + COMMAND_WORD + "\n"
        + "2. " + COMMAND_WORD + " l/bedroom\n";

    private final Predicate<Item> predicate;

    public ClearCommand(LocationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public ClearCommand() {
        this.predicate = PREDICATE_SHOW_ALL_ITEMS;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredItemList(predicate);
        List<Item> filteredList = model.getFilteredItemList();
        int numberOfItems = filteredList.size();
        for (int i = 0; i < numberOfItems; i++) {
            model.deleteItem(filteredList.get(0));
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ClearCommand // instanceof handles nulls
            && predicate.equals(((ClearCommand) other).predicate)); // state check

    }

}
