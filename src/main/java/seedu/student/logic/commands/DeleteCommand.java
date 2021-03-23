package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.student.commons.core.Messages;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.MatriculationNumberContainsKeywordsPredicate;
import seedu.student.model.student.Student;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "deleteStud";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their unique matriculation number assigned by NUS.\n"
            + "Parameters: Matriculation Number \n"
            + "Example: " + COMMAND_WORD + " A1234567X";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s"; // add name + matric number

    private final MatriculationNumber matriculationNumber;
    private final MatriculationNumberContainsKeywordsPredicate predicate;


    /**
     * Creates a DeleteCommand object responsible for deleting a student by matriculation number.
     *
     * @param matriculationNumber Matriculation number of the student you want to delete.
     */
    public DeleteCommand(MatriculationNumber matriculationNumber) {
        this.matriculationNumber = matriculationNumber;

        predicate = new MatriculationNumberContainsKeywordsPredicate(matriculationNumber.toString());
    }

    /**
     * @param studentList List of all students in Vax@NUS system.
     * @param matricNum Matriculation Number of the student you want to delete.
     * @return Student you want to delete, null if the matriculation number does not exist in System.
     */
    public static Student getStudent(List<Student> studentList, MatriculationNumber matricNum) {
        assert studentList != null;
        assert MatriculationNumber.isValidMatric(matricNum.value);
        for (Student p : studentList) {
            if (p.getMatriculationNumber().equals(matricNum)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> studentList = model.getStudentBook().getStudentList();

        Student studentToDelete = getStudent(studentList, matriculationNumber);
        if (studentToDelete == null) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_MATRIC_NUM);
        }
        model.deleteStudent(studentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && matriculationNumber.equals(((DeleteCommand) other).matriculationNumber)); // state check
    }
}
