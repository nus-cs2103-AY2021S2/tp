package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_EXPIRYDATE;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.storemando.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.function.Predicate;

import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = "sorts the items in StoreMando by quantity or expiry date. "
        + "Parameter:"
        + PREFIX_QUANTITY
        + "or "
        + PREFIX_EXPIRYDATE;

    public static final String MESSAGE_SUCCESS = "sorted all items";

    private final Predicate<Item> predicate;

    public SortCommand(LocationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public SortCommand() {
        this.predicate = PREDICATE_SHOW_ALL_ITEMS;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredItemList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ListCommand // instanceof handles nulls
            && predicate.equals(((ListCommand) other).predicate)); // state check

    }
}
