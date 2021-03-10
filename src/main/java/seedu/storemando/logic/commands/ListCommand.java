package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.function.Predicate;

import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;
import seedu.storemando.model.tag.TagContainsKeywordsPredicate;

/**
 * Lists all items in the storemando to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all items";

    private final Predicate<Item> predicate;

    public ListCommand(LocationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public ListCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public ListCommand() {
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
