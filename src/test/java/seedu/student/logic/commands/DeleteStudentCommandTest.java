package seedu.student.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.logic.commands.CommandTestUtil.showStudentWithMatricNum;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FIRST_STUDENT;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.student.commons.core.Messages;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.UserPrefs;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;
import seedu.student.testutil.TypicalAppointments;
import seedu.student.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class DeleteStudentCommandTest {

    private Model modelWithNoAppointments = new ModelManager(TypicalStudents.getTypicalStudentBook(), new UserPrefs());
    private Model modelWithAppointments = new ModelManager(TypicalAppointments.getTypicalStudentBook(),
            new UserPrefs());

    @Test
    public void execute_invalidMatricNumUnfilteredList_throwsCommandException() {
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(new MatriculationNumber(
                MATRIC_NUMBER_FIRST_STUDENT));

        assertCommandFailure(deleteStudentCommand, modelWithNoAppointments, Messages.MESSAGE_NONEXISTENT_MATRIC_NUM);
    }

    @Test
    public void execute_validMatricNumFilteredList_success() throws ParseException {
        MatriculationNumber matricNumberToDelete = TypicalStudents.ALICE.getMatriculationNumber();
        showStudentWithMatricNum(modelWithNoAppointments, matricNumberToDelete);

        Student studentToDelete = modelWithNoAppointments.getStudent(matricNumberToDelete);
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(matricNumberToDelete);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        Model expectedModel = new ModelManager(modelWithNoAppointments.getStudentBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        showNoStudent(expectedModel);

        assertCommandSuccess(deleteStudentCommand, modelWithNoAppointments, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMatricNumWithAppt_success() {
        MatriculationNumber matricNumberToDelete = TypicalStudents.ALICE.getMatriculationNumber();

        Student studentToDelete = modelWithAppointments.getStudent(matricNumberToDelete);
        Appointment apptOfStudentToDelete = modelWithAppointments.getAppointment(matricNumberToDelete);

        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(matricNumberToDelete);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        Model expectedModel = new ModelManager(modelWithAppointments.getStudentBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        if (apptOfStudentToDelete != null) {
            expectedModel.deleteAppointment(apptOfStudentToDelete);
        }

        assertCommandSuccess(deleteStudentCommand, modelWithAppointments, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteStudentCommand deleteFirstCommand = new DeleteStudentCommand(new MatriculationNumber(
                MATRIC_NUMBER_FIRST_STUDENT));
        DeleteStudentCommand deleteSecondCommand = new DeleteStudentCommand(new MatriculationNumber(
                MATRIC_NUMBER_SECOND_STUDENT));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStudentCommand deleteFirstCommandCopy = new DeleteStudentCommand(new MatriculationNumber(
                MATRIC_NUMBER_FIRST_STUDENT));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(p -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}
