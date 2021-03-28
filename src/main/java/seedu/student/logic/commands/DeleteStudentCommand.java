package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.student.commons.core.Messages;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteStudentCommand extends Command {

    public static final String COMMAND_WORD = "deleteStud";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by their unique matriculation number assigned by NUS.\n"
            + "Parameters: Matriculation Number \n"
            + "Example: " + COMMAND_WORD + " A1234567X";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s"; // add name + matric number

    private final MatriculationNumber matriculationNumber;


    /**
     * Creates a DeleteStudentCommand object responsible for deleting a student by matriculation number.
     *
     * @param matriculationNumber Matriculation number of the student you want to delete.
     */
    public DeleteStudentCommand(MatriculationNumber matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Student studentToDelete = model.getStudent(matriculationNumber);
        if (studentToDelete == null) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_MATRIC_NUM);
        }
        model.deleteStudent(studentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStudentCommand // instanceof handles nulls
                && matriculationNumber.equals(((DeleteStudentCommand) other).matriculationNumber)); // state check
    }
}
