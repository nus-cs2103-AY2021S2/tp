package seedu.student.logic.commands.findcommands;

import seedu.student.logic.commands.Command;
import seedu.student.logic.commands.CommandResult;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.appointment.AppointmentContainsMatriculationNumberPredicate;
import seedu.student.model.appointment.AppointmentListContainsMatriculationNumberPredicate;
import seedu.student.model.student.StudentContainsMatriculationNumberPredicate;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds student and appointment whose "
            + "matriculation number matches the specified keywords (case-sensitive) and displays it.\n"
            + "Parameters: KEYWORD \n"
            + "Example: " + COMMAND_WORD + " A01234567R";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
