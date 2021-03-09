package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.Quantity;
import seedu.storemando.model.item.QuantityComparator;

public class SortQuantityCommand extends SortCommand {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        QuantityComparator comparator = new QuantityComparator();
        model.sortFilteredItemList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortQuantityCommand);
    }
}
