package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_EVENT;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.identifier.Identifier;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventStatus;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DoneCommand}.
 */
public class DoneCommandTest {

    private Model model = new ModelManager(new UserPrefs(), getTypicalEventBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToBeDone = model.getEventBook().getEventList().get(IDENTIFIER_SECOND_EVENT.getZeroBased());
        Identifier eventIdentifier = Identifier.fromIdentifier(eventToBeDone.getIdentifier());
        DoneCommand doneCommand = new DoneCommand(eventIdentifier);

        Event doneEvent = new Event(eventToBeDone.getName(), EventStatus.DONE, eventToBeDone.getPriority(),
                eventToBeDone.getDescription(), eventIdentifier.getValue());
        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_EVENT_SUCCESS, doneEvent);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), new EventBook(model.getEventBook()));
        expectedModel.setEvent(eventToBeDone, doneEvent);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Identifier outOfBoundIndex = Identifier.fromIdentifier(Event.getLatestIdentifier().getValue() + 1);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model,
                String.format(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER, outOfBoundIndex.getValue()));
    }

    @Test
    public void execute_eventIsAlreadyDone_fail() {
        Event eventToMarkAsDone = model.getEventBook().getEventList().get(IDENTIFIER_FIRST_EVENT.getZeroBased());
        Identifier eventIdentifier = Identifier.fromIdentifier(eventToMarkAsDone.getIdentifier());
        DoneCommand doneCommand = new DoneCommand(eventIdentifier);

        String expectedMessage = DoneCommand.MESSAGE_DONE_ALR_EVENT;

        assertCommandFailure(doneCommand, model, expectedMessage);
    }

    @Test
    public void execute_emptyEventBook_fail() {
        model = new ModelManager(new UserPrefs(), new EventBook());
        DoneCommand doneCommand = new DoneCommand(IDENTIFIER_FIRST_EVENT);

        String expectedMessage = Messages.MESSAGE_INVALID_EVENT_INDEX_NO_EVENTS;

        assertCommandFailure(doneCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DoneCommand doneFirstCommand = new DoneCommand(IDENTIFIER_FIRST_EVENT);
        DoneCommand doneSecondCommand = new DoneCommand(IDENTIFIER_SECOND_EVENT);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneCommand doneFirstCommandCopy = new DoneCommand(IDENTIFIER_FIRST_EVENT);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }

}
