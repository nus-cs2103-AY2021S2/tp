package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds an object to the address book.
 */
public abstract class AddCommand extends Command {

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
