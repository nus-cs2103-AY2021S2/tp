package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSessionIds.SESSION_ID_FIRST_CLASS;
import static seedu.address.testutil.TypicalSessionIds.SESSION_ID_SECOND_CLASS;
import static seedu.address.testutil.TypicalSessions.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteSessionCommand}.
 */
public class DeleteSessionCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validSessionIdFilteredList_success() {
        Optional<Session> sessionToDelete = model.getFilteredSessionList().stream()
                .filter(x-> x.getClassId().equals(SESSION_ID_FIRST_CLASS)).findAny();
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(SESSION_ID_FIRST_CLASS);

        String expectedMessage = DeleteSessionCommand.MESSAGE_DELETE_SESSION_SUCCESS + String.format(Messages
                        .MESSAGE_SESSION_PLACEHOLDER, sessionToDelete.get());

        Optional<Session> expectedSessionToDelete = expectedModel.getFilteredSessionList().stream()
                .filter(x-> x.getClassId().equals(SESSION_ID_FIRST_CLASS)).findAny();

        assertCommandSuccess(deleteSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidSessionIdUnfilteredList_throwsCommandException() {
        SessionId outOfBoundSessionId = SessionId.fromOneBased(model.getFilteredSessionList().size() + 1);
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(outOfBoundSessionId);

        assertCommandFailure(deleteSessionCommand, model, Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
    }



    @Test
    public void equals() {
        DeleteSessionCommand deleteFirstSessionCommand = new DeleteSessionCommand(SESSION_ID_FIRST_CLASS);
        DeleteSessionCommand deleteSecondSessionCommand = new DeleteSessionCommand(SESSION_ID_SECOND_CLASS);

        // same object -> returns true
        assertTrue(deleteFirstSessionCommand.equals(deleteFirstSessionCommand));

        // same values -> returns true
        DeleteSessionCommand deleteFirstSessionCommandCopy = new DeleteSessionCommand(SESSION_ID_FIRST_CLASS);
        assertTrue(deleteFirstSessionCommand.equals(deleteFirstSessionCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstSessionCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstSessionCommand.equals(null));

        // different session -> returns false
        assertFalse(deleteFirstSessionCommand.equals(deleteSecondSessionCommand));
    }

}
