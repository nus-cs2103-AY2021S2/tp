package seedu.budgetbaby.ablogic.commands;

import seedu.budgetbaby.abmodel.Model;
import seedu.budgetbaby.logic.commands.CommandResult;
import seedu.budgetbaby.logic.commands.exceptions.CommandException;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}
