package seedu.student.logic.commands;

import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.Model;
import seedu.student.model.student.exceptions.MatriculationNumberDoesNotExistException;

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
    public abstract CommandResult execute(Model model) throws CommandException, ParseException,
            MatriculationNumberDoesNotExistException;

}
