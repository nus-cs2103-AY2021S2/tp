package chim.logic.commands;

import chim.logic.commands.exceptions.CommandException;
import chim.model.Model;

/**
 * Edits the details of a customer, an order, or a cheese in CHIM.
 */
public abstract class EditCommand extends Command {

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
