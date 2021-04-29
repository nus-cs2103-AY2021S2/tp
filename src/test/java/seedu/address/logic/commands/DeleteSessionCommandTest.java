package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_SESSION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.session.Session;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteSessionCommand}.
 */
class DeleteSessionCommandTest {
    // TODO: add delete session related tests.

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model deletionModel;

    private void setup() {
        AddressBook deletionAddressBook = new AddressBook();
        deletionAddressBook.addStudent(new StudentBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withStudyLevel("Sec 2").withGuardianPhone("82813844")
                .withRelationship("Mother")
                .addSessions(
                        new SessionBuilder().withSessionDate("2021-02-05", "12:00").withFee("39.40").build(),
                        new SessionBuilder().withSessionDate("2021-02-01", "12:00").withFee("81.50").build(),
                        new SessionBuilder().withSessionDate("2021-03-01", "12:00").withFee("29.31").build(),
                        new SessionBuilder().withSessionDate("2020-02-01", "12:00").withFee("50.28").build()
                )
                .build());
        deletionModel = new ModelManager(deletionAddressBook, new UserPrefs());

    }
    @Test
    public void execute_validNameValidIndex_success() throws CommandException {
        setup();
        Student studentWithSession = deletionModel.getAddressBook().getStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased());
        Session sessionToDelete = studentWithSession.getListOfSessions().get(INDEX_FIRST_SESSION.getZeroBased());
        DeleteSessionCommand deleteSessionCommand =
                new DeleteSessionCommand(studentWithSession.getName(), INDEX_FIRST_SESSION);

        String expectedMessage = String.format(DeleteSessionCommand.MESSAGE_DELETE_SESSION_SUCCESS,
                sessionToDelete.toString());

        AddressBook expectedAddressBook = new AddressBook();
        expectedAddressBook.addStudent(new StudentBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withStudyLevel("Sec 2").withGuardianPhone("82813844")
                .withRelationship("Mother")
                .addSessions(
                        new SessionBuilder().withSessionDate("2021-02-05", "12:00").withFee("39.40").build(),
                        new SessionBuilder().withSessionDate("2021-02-01", "12:00").withFee("81.50").build(),
                        new SessionBuilder().withSessionDate("2021-03-01", "12:00").withFee("29.31").build(),
                        new SessionBuilder().withSessionDate("2020-02-01", "12:00").withFee("50.28").build()
                )
                .build());
        ModelManager expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());
        expectedModel.deleteSession(studentWithSession.getName(), INDEX_FIRST_SESSION);

        assertCommandSuccess(deleteSessionCommand, deletionModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        setup();
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(new Name("ABC"), INDEX_FIRST_SESSION);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_NAME, () ->
                deleteSessionCommand.execute(deletionModel));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        setup();
        Student studentWithSession = deletionModel.getAddressBook().getStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased());
        DeleteSessionCommand deleteSessionCommand =
                new DeleteSessionCommand(studentWithSession.getName(), INDEX_FIFTH_SESSION);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX, () ->
                deleteSessionCommand.execute(deletionModel));
    }


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
}
