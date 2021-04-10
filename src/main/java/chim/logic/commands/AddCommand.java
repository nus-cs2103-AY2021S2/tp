package chim.logic.commands;

import chim.logic.commands.exceptions.CommandException;
import chim.model.Model;

/**
 * Adds a customer, an order, or cheeses to CHIM.
 */
public abstract class AddCommand extends Command {

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
