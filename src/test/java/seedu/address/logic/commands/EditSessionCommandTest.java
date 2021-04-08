package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FIRST_SESSION;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SECOND_SESSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_FIRST_SESSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_FIRST_SESSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SESSION;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSessionIds.SESSION_ID_FIRST_CLASS;
import static seedu.address.testutil.TypicalSessionIds.SESSION_ID_SECOND_CLASS;
import static seedu.address.testutil.TypicalSessions.getTypicalAddressBook;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionId;
import seedu.address.testutil.EditSessionDescriptorBuilder;
import seedu.address.testutil.SessionBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditSessionCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Session editedSession = new SessionBuilder().build();
        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder(editedSession).build();
        EditSessionCommand editSessionCommand = new EditSessionCommand(SESSION_ID_FIRST_CLASS, descriptor);

        String expectedMessage = EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS + String.format(Messages
                            .MESSAGE_SESSION_PLACEHOLDER, editedSession);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        List<Session> lastShownList = model.getFilteredSessionList();

        Optional<Session> optSessionToEdit = lastShownList.stream()
                .filter(x-> x.getClassId().equals(SESSION_ID_FIRST_CLASS)).findAny();

        Session sessionToEdit = optSessionToEdit.get();

        expectedModel.setSession(sessionToEdit, editedSession);

        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        int size = model.getFilteredSessionList().size() - 1;
        Session lastSession = model.getFilteredSessionList().get(size);
        SessionId lastSessionId = lastSession.getClassId();

        SessionBuilder sessionInList = new SessionBuilder(lastSession);
        Session editedSession = sessionInList.withDay(VALID_DAY_FIRST_SESSION).withSubject(VALID_SUBJECT_FIRST_SESSION)
                .withTags(VALID_TAG_SESSION).build();

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withDay(VALID_DAY_FIRST_SESSION).withSubject(VALID_SUBJECT_FIRST_SESSION)
                .withTags(VALID_TAG_SESSION).build();
        EditSessionCommand editSessionCommand = new EditSessionCommand(lastSessionId, descriptor);

        String expectedMessage = EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS + String.format(Messages
                                .MESSAGE_SESSION_PLACEHOLDER, editedSession);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSession(lastSession, editedSession);

        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditSessionCommand editSessionCommand = new EditSessionCommand(SESSION_ID_FIRST_CLASS,
                new EditSessionCommand.EditSessionDescriptor());
        List<Session> lastShownList = model.getFilteredSessionList();

        Optional<Session> optSessionToEdit = lastShownList.stream()
                .filter(x-> x.getClassId().equals(SESSION_ID_FIRST_CLASS)).findAny();

        Session sessionToEdit = optSessionToEdit.get();

        String expectedMessage = EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS + String.format(Messages
                                .MESSAGE_SESSION_PLACEHOLDER, sessionToEdit);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Integer outOfBoundSessionCount = Integer.valueOf(Session.getSessionCount()) + 1;
        SessionId invalidSessionId = SessionId.fromOneBased(outOfBoundSessionCount);
        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder().build();
        EditSessionCommand editSessionCommand = new EditSessionCommand(invalidSessionId, descriptor);

        assertCommandFailure(editSessionCommand, model, Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        final EditSessionCommand standardCommand = new EditSessionCommand(SESSION_ID_FIRST_CLASS, DESC_FIRST_SESSION);

        // same values -> returns true
        EditSessionCommand.EditSessionDescriptor copyDescriptor = new EditSessionCommand
                .EditSessionDescriptor(DESC_FIRST_SESSION);
        EditSessionCommand commandWithSameValues = new EditSessionCommand(SESSION_ID_FIRST_CLASS, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different Session Id -> returns false
        assertFalse(standardCommand.equals(new EditSessionCommand(SESSION_ID_SECOND_CLASS, DESC_FIRST_SESSION)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditSessionCommand(SESSION_ID_FIRST_CLASS, DESC_SECOND_SESSION)));
    }

}
