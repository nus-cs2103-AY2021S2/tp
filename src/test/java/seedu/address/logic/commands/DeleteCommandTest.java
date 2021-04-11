package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIdentifier;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_EVENT;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.identifier.Identifier;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(new UserPrefs(), getTypicalEventBook());


    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToDelete = model.getEventBook().getEventList().get(IDENTIFIER_FIRST_EVENT.getZeroBased());
        Identifier eventIdentifier = Identifier.fromIdentifier(eventToDelete.getIdentifier());
        DeleteCommand deleteCommand = new DeleteCommand(eventIdentifier);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getEventBook());
        expectedModel.deleteEvent(eventToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Identifier outOfBoundIndex = Identifier.fromIdentifier(Event.getLatestIdentifier().getValue() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER,
                outOfBoundIndex.getValue());

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexfilteredList_success() {
        int sizeOfEventBook = model.getEventBook().getEventList().size();
        Event eventToDelete = model.getEventBook().getEventList().get(sizeOfEventBook - 1);
        Event secondEvent = model.getEventBook().getEventList().get(IDENTIFIER_SECOND_EVENT.getZeroBased());
        Identifier eventIdentifier = Identifier.fromIdentifier(eventToDelete.getIdentifier());
        Identifier secondEventIdentifier = Identifier.fromIdentifier(secondEvent.getIdentifier());
        showEventAtIdentifier(model, secondEventIdentifier);

        DeleteCommand deleteCommand = new DeleteCommand(eventIdentifier);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getEventBook());
        expectedModel.deleteEvent(eventToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(IDENTIFIER_FIRST_EVENT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(IDENTIFIER_SECOND_EVENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(IDENTIFIER_FIRST_EVENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no events.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        assertTrue(model.getFilteredEventList().isEmpty());
    }
}
