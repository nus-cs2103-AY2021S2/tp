package seedu.storemando.logic.commands;

import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;

public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = "sorts the items in StoreMando by quantity or expiry date. "
        + "Parameter:"
        + "quantity"
        + "or "
        + "expiryDate";

    public static final String MESSAGE_SUCCESS = "sorted all items";

    public static final String MESSAGE_NO_ITEMS_TO_SORT = "Empty list cannot be sorted";


    public abstract CommandResult execute(Model model) throws CommandException;

    public abstract boolean equals(Object other);
}
