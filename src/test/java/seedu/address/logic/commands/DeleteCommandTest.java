package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentWithMatricNum;
import static seedu.address.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FIRST_STUDENT;
import static seedu.address.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FOURTH_STUDENT;
import static seedu.address.testutil.TypicalMatricNumbers.MATRIC_NUMBER_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.MatriculationNumber;
import seedu.address.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void execute_validMatricNumUnfilteredList_success() {
        List<Student> studentListTest = model.getFilteredStudentList();
        MatriculationNumber matricNumberToDelete = new MatriculationNumber(MATRIC_NUMBER_FOURTH_STUDENT);
        Student studentToDelete = DeleteCommand.getStudent(studentListTest, matricNumberToDelete);
        DeleteCommand deleteCommand = new DeleteCommand(matricNumberToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        ModelManager expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMatricNumUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(new MatriculationNumber(MATRIC_NUMBER_FIRST_STUDENT));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NONEXISTENT_MATRIC_NUM);
    }

    @Test
    public void execute_validMatricNumFilteredList_success() {
        MatriculationNumber matricNumberToDelete = new MatriculationNumber(MATRIC_NUMBER_FOURTH_STUDENT);
        showStudentWithMatricNum(model, matricNumberToDelete);

        List<Student> studentListTest = model.getFilteredStudentList();
        Student studentToDelete = DeleteCommand.getStudent(studentListTest, matricNumberToDelete);
        DeleteCommand deleteCommand = new DeleteCommand(matricNumberToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);


        Model expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        showNoStudent(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }



    //    @Test
    //    public void execute_invalidIndexFilteredList_throwsCommandException() {
    //        MatriculationNumber matricNumberToDelete = new MatriculationNumber(MATRIC_NUMBER_FIRST_STUDENT);
    //        // showStudentWithMatricNum(model, matricNumberToDelete);
    //
    //        DeleteCommand deleteCommand = new DeleteCommand(matricNumberToDelete);
    //
    //        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NONEXISTENT_MATRIC_NUM);
    //    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(new MatriculationNumber(MATRIC_NUMBER_FIRST_STUDENT));
        DeleteCommand deleteSecondCommand = new DeleteCommand(new MatriculationNumber(MATRIC_NUMBER_SECOND_STUDENT));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(new MatriculationNumber(MATRIC_NUMBER_FIRST_STUDENT));
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
