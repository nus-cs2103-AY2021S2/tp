package seedu.storemando.logic.commands;

import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;

public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Sorts the items in StoreMando by quantity or expiry date.\n"
        + "Parameter (case-insensitive): quantity asc, quantity desc OR expirydate\n"
        + "Examples: \n"
        + "1. " + COMMAND_WORD + " quantity asc\n"
        + "2. " + COMMAND_WORD + " quantity desc\n"
        + "3. " + COMMAND_WORD + " expirydate";

    public static final String MESSAGE_SUCCESS = "Sorted all items";

    public static final String MESSAGE_NO_ITEMS_TO_SORT = "Empty list cannot be sorted";


    public abstract CommandResult execute(Model model) throws CommandException;

    public abstract boolean equals(Object other);
}
