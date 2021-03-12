package seedu.storemando.logic.commands;

import seedu.storemando.model.Model;

public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = "sorts the items in StoreMando by quantity or expiry date. "
        + "Parameter:"
        + "quantity"
        + "or "
        + "expiryDate";

    public static final String MESSAGE_SUCCESS = "sorted all items";


    public abstract CommandResult execute(Model model);

    public abstract boolean equals(Object other);
}
