package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSessionIds.SESSION_ID_FIRST_CLASS;
import static seedu.address.testutil.TypicalSessionIds.SESSION_ID_INVALID_CLASS;
import static seedu.address.testutil.TypicalSessionIds.SESSION_ID_SECOND_CLASS;
import static seedu.address.testutil.TypicalSessions.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.session.SessionIdPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewSessionCommand.
 */
public class ViewSessionCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_viewSession_success() {
        expectedModel.updateFilteredSessionList(new SessionIdPredicate(SESSION_ID_FIRST_CLASS));
        assertCommandSuccess(new ViewSessionCommand(new SessionIdPredicate(SESSION_ID_FIRST_CLASS)),
                model, ViewSessionCommand.MESSAGE_SUCCESS, expectedModel);
        assertTrue(model.getFilteredSessionList().equals(expectedModel.getFilteredSessionList()));
    }

    @Test
    public void execute_viewSession_invalidSessionId() {
        assertCommandFailure(new ViewSessionCommand(new SessionIdPredicate(SESSION_ID_INVALID_CLASS)),
                model, Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewSessionCommand viewFirstSessionCommand = new ViewSessionCommand(new SessionIdPredicate(
                                                SESSION_ID_FIRST_CLASS));
        ViewSessionCommand viewSecondSessionCommand = new ViewSessionCommand(new SessionIdPredicate(
                                                SESSION_ID_SECOND_CLASS));

        // same object -> returns true
        assertTrue(viewFirstSessionCommand.equals(viewFirstSessionCommand));

        // same values -> returns true
        ViewSessionCommand viewFirstSessionCommandCopy = new ViewSessionCommand(new SessionIdPredicate(
                                                        SESSION_ID_FIRST_CLASS));
        assertTrue(viewFirstSessionCommand.equals(viewFirstSessionCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstSessionCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstSessionCommand.equals(null));

        // different session -> returns false
        assertFalse(viewFirstSessionCommand.equals(viewSecondSessionCommand));
    }
}
