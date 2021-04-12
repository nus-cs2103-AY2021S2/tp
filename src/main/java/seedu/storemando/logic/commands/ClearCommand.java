package seedu.storemando.logic.commands;

import static seedu.storemando.logic.parser.CliSyntax.PREFIX_LOCATION;

import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;

/**
 * Clears the storemando or a specified location.
 */
public abstract class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clear all items in StoreMando or a specified "
        + "location.\n"
        + "Parameters: "
        + "[" + PREFIX_LOCATION + "LOCATION]\n"
        + "Example:\n"
        + "1. " + COMMAND_WORD + "\n"
        + "2. " + COMMAND_WORD + " l/bedroom\n";
    public static final String MESSAGE_NO_ITEMS_IN_STOREMANDO = "There are no items in the inventory.";

    public abstract CommandResult execute(Model model) throws CommandException;

    public abstract boolean equals(Object other);

}
