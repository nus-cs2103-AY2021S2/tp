package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.storemando.model.Model;
import seedu.storemando.model.item.ExpiryDateComparator;

public class SortExpiryDateCommand extends SortCommand {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ExpiryDateComparator comparator = new ExpiryDateComparator();
        model.updateSortedItemList(comparator);
        model.setItems(model.getSortedItemList());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortExpiryDateCommand);
    }
}
