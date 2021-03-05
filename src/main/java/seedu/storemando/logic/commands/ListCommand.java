package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.storemando.model.Model;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;

/**
 * Lists all items in the storemando to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all items";

    private final LocationContainsKeywordsPredicate predicate;

    public ListCommand(LocationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public ListCommand() {
        this.predicate = new LocationContainsKeywordsPredicate(new ArrayList<>());
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(predicate);
        return new CommandResult(MESSAGE_SUCCESS); // might need change
    }
}
