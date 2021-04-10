package chim.logic.commands;

import chim.logic.commands.exceptions.CommandException;
import chim.model.Model;

/**
 * Deletes a customer, an order, or a cheese from CHIM.
 */
public abstract class DeleteCommand extends Command {

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
