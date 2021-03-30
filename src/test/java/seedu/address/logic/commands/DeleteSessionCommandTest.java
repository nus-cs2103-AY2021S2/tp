package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteSessionCommand}.
 */
class DeleteSessionCommandTest {
    // TODO: add delete session related tests.

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /*
    @Test
    public void execute_validNameValidIndex_success() {
        Student studentWithSession = model.getAddressBook().getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Session sessionToDelete = studentWithSession.getListOfSessions().get(INDEX_FIRST_SESSION.getZeroBased());
        DeleteSessionCommand deleteSessionCommand =
                new DeleteSessionCommand(studentWithSession.getName(), INDEX_FIRST_SESSION);

        String expectedMessage = String.format(DeleteSessionCommand.MESSAGE_DELETE_SESSION_SUCCESS, sessionToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteSession(studentWithSession.getName(), INDEX_FIRST_SESSION);


            CommandResult actual = deleteSessionCommand.execute(model);
            assertCommandSuccess(deleteSessionCommand, model, new CommandResult(expectedMessage), expectedModel);
        assertCommandSuccess(deleteSessionCommand, model, expectedMessage, expectedModel);
    }
     */


    @Test
    public void equals() {
        Student studentAWithSession = model.getAddressBook().getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Name nameA = studentAWithSession.getName();
        Student studentBWithSession = model.getAddressBook().getStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());

        Name nameB = studentBWithSession.getName();

        DeleteSessionCommand deleteCommandA = new DeleteSessionCommand(nameA, INDEX_FIRST_SESSION);
        DeleteSessionCommand deleteCommandB = new DeleteSessionCommand(nameB, INDEX_FIRST_SESSION);

        // same object -> returns true
        assertTrue(deleteCommandA.equals(deleteCommandA));

        // same values -> returns true
        DeleteSessionCommand deleteCommandACopy = new DeleteSessionCommand(nameA, INDEX_FIRST_SESSION);
        assertTrue(deleteCommandA.equals(deleteCommandACopy));

        // different types -> returns false
        assertFalse(deleteCommandA.equals(1));

        // null -> returns false
        assertFalse(deleteCommandA.equals(null));

        // different student -> returns false
        assertFalse(deleteCommandA.equals(deleteCommandB));
    }

    /*
    @Test
    public void execute_validNameInvalidIndex_throwsCommandException() {
        Student studentWithSession = model.getAddressBook().getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        Index outOfBoundIndex = INDEX_SECOND_SESSION;
        assertTrue(outOfBoundIndex.getZeroBased() > studentWithSession.getListOfSessions().size());

        DeleteSessionCommand deleteSessionCommand =
                new DeleteSessionCommand(studentWithSession.getName(), outOfBoundIndex);


        assertCommandFailure(deleteSessionCommand, model, Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
    }
    */
}
