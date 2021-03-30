package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_EVENT;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_SECOND_EVENT;

import org.junit.jupiter.api.Test;

//import seedu.address.commons.core.Messages;
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

    /*
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Identifier outOfBoundIndex = Identifier.fromIdentifier(model.getEventBook().getEventList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER);
    }
     */

    /*
    @Test
    public void execute_validIndexFilteredList_success() {
        showEventAtIdentifier(model, IDENTIFIER_FIRST_EVENT);

        Event eventToDelete = model.getFilteredEventList().get(IDENTIFIER_FIRST_EVENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(IDENTIFIER_FIRST_EVENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        Model expectedModel = new ModelManager(new UserPrefs(), model.getEventBook());
        expectedModel.deleteEvent(eventToDelete);
        showNoEvent(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEventAtIdentifier(model, IDENTIFIER_FIRST_EVENT);

        Identifier outOfBoundIndex = IDENTIFIER_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEventBook().getEventList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER);
    }
     */

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
