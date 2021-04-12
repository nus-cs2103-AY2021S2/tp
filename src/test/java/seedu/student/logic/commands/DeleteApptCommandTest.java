package seedu.student.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.testutil.TypicalAppointments.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.UserPrefs;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;
import seedu.student.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteApptCommand}.
 */
public class DeleteApptCommandTest {
    // getTypicalStudentBook() called from TypicalAppointments, contains typical appointments
    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void execute_validMatricNumUnfilteredList_success() {
        MatriculationNumber matricNumberToDelete = TypicalStudents.ALICE.getMatriculationNumber(); // A3456789D
        Appointment apptToDelete = model.getAppointment(matricNumberToDelete);
        Student student = model.getStudent(matricNumberToDelete);

        DeleteApptCommand deleteApptCommand = new DeleteApptCommand(matricNumberToDelete);

        String expectedMessage = String.format(DeleteApptCommand.MESSAGE_DELETE_APPT_SUCCESS,
                student.getName(), apptToDelete);

        ModelManager expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
        expectedModel.deleteAppointment(apptToDelete);

        assertCommandSuccess(deleteApptCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMatricNumUnfilteredList_throwsCommandException() {
        MatriculationNumber matricNumberToDelete = TypicalStudents.HOON.getMatriculationNumber(); // Does not exist
        DeleteApptCommand deleteApptCommand = new DeleteApptCommand(matricNumberToDelete);
        String expectedOutput = String.format(DeleteApptCommand.MESSAGE_NONEXISTENT_APPT, matricNumberToDelete);
        assertCommandFailure(deleteApptCommand, model, expectedOutput);
    }

    @Test
    public void equals() {
        MatriculationNumber matricNumberAlice = TypicalStudents.ALICE.getMatriculationNumber(); // A3456789D
        MatriculationNumber matricNumberHoon = TypicalStudents.HOON.getMatriculationNumber();
        DeleteApptCommand deleteApptFirstCommand = new DeleteApptCommand(matricNumberAlice);
        DeleteStudentCommand deleteApptSecondCommand = new DeleteStudentCommand(matricNumberHoon);

        // same object -> returns true
        assertTrue(deleteApptFirstCommand.equals(deleteApptFirstCommand));

        // same values -> returns true
        DeleteApptCommand deleteApptFirstCommandCopy = new DeleteApptCommand(matricNumberAlice);
        assertTrue(deleteApptFirstCommand.equals(deleteApptFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteApptFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteApptFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteApptFirstCommand.equals(deleteApptSecondCommand));
    }


}
