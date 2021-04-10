package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Describes behaviour of commands which can be executed in batch.
 */
public interface BatchOperation {
    /**
     * Executes the command and returns the result message. Does not update the filtered list to show all the other
     * client contacts.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult executeBatch(Model model) throws CommandException;
}
