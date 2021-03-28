package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTOR_ID;

import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.exceptions.AlreadyEnrolledException;
import seedu.address.model.person.exceptions.StudentTutorNotFoundException;
import seedu.address.model.person.exceptions.TimeClashException;
import seedu.address.model.session.exceptions.SessionNotFoundException;


public class AssignCommand extends Command {
    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns student(s) and/or tutor to a class \n"
            + "Parameters: " + "(assign student ONLY, tutor Only or Both) "
            + PREFIX_STUDENT_ID + "StudentId "
            + PREFIX_TUTOR_ID + "TutorId "
            + PREFIX_CLASS_ID + "ClassId \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "3 "
            + PREFIX_TUTOR_ID + "2 "
            + PREFIX_CLASS_ID + "1 ";

    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";

    private final Assignment assignment;

    /**
     * Creates an AddSessionCommand to add the specified {@code Person}
     */
    public AssignCommand(Assignment assignment) {
        requireNonNull(assignment);
        this.assignment = assignment;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        try {
            model.registerAssignment(this.assignment);
        } catch (TimeClashException | AlreadyEnrolledException
                | SessionNotFoundException | StudentTutorNotFoundException e) {
            return new CommandResult(e.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, assignment));
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignCommand // instanceof handles nulls
                && assignment.equals(((AssignCommand) other).assignment));
    }
}
