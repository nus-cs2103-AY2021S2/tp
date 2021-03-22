package seedu.us.among.logic.commands;

import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.endpoint.exceptions.AbortRequestException;
import seedu.us.among.logic.endpoint.exceptions.RequestException;
import seedu.us.among.model.Model;

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
    public abstract CommandResult execute(Model model) throws CommandException, RequestException, AbortRequestException;

}
