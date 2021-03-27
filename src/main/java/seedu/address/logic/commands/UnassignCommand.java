package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTOR_ID;

import seedu.address.model.Model;
import seedu.address.model.assignment.Unassignment;
import seedu.address.model.assignment.exceptions.UnassignTutorException;
import seedu.address.model.person.exceptions.StudentTutorNotFoundException;
import seedu.address.model.session.exceptions.SessionNotFoundException;


public class UnassignCommand extends Command {
    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unassigns student(s) and/or tutor from a class \n"
            + "Parameters: " + " "
            + PREFIX_STUDENT_ID + "StudentId "
            + PREFIX_TUTOR_ID + "TutorId "
            + PREFIX_CLASS_ID + "ClassId\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "3 "
            + PREFIX_TUTOR_ID + "2 "
            + PREFIX_CLASS_ID + "1";

    public static final String MESSAGE_SUCCESS = "Unassigned: %1$s";

    private final Unassignment unassignment;

    /**
     * Creates an AddSessionCommand to add the specified {@code Person}
     */
    public UnassignCommand(Unassignment unassignment) {
        requireNonNull(unassignment);
        this.unassignment = unassignment;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        try {
            model.registerUnassignment(this.unassignment);
        } catch (SessionNotFoundException | StudentTutorNotFoundException | UnassignTutorException e) {
            return new CommandResult(e.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, unassignment));
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnassignCommand // instanceof handles nulls
                && unassignment.equals(((UnassignCommand) other).unassignment));
    }
}
