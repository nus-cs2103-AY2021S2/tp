package seedu.partyplanet.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.partyplanet.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.partyplanet.logic.commands.CommandTestUtil.showEventAtMultipleIndex;
import static seedu.partyplanet.testutil.Assert.assertThrows;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.partyplanet.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EDeleteEventCommand}.
 */
public class EDeleteEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void execute_validSingleIndexUnfilteredList_success() {
        Event eventToDelete = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EDeleteCommand eDeleteCommand = new EDeleteEventCommand(List.of(INDEX_FIRST_EVENT), List.of());

        String expectedMessage = String.format(EDeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                eventToDelete.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);

        assertCommandSuccess(eDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSingleIndexFilteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventToDelete = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EDeleteCommand eDeleteCommand = new EDeleteEventCommand(List.of(INDEX_FIRST_EVENT), List.of());

        String expectedMessage = String.format(EDeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                eventToDelete.getName());

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);
        showNoEvent(expectedModel);

        assertCommandSuccess(eDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndexUnfilteredList_success() {

        List<Event> eventsToDelete = new ArrayList<>();
        eventsToDelete.add(model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased()));
        eventsToDelete.add(model.getFilteredEventList().get(INDEX_SECOND_EVENT.getZeroBased()));

        EDeleteCommand eDeleteCommand = new EDeleteEventCommand(
                List.of(INDEX_FIRST_EVENT, INDEX_SECOND_EVENT), List.of());

        String expectedMessage = String.format(EDeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                displayEvents(eventsToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Event p : eventsToDelete) {
            expectedModel.deleteEvent(p);
        }

        assertCommandSuccess(eDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndexFilteredList_success() {
        showEventAtMultipleIndex(model, INDEX_FIRST_EVENT, INDEX_SECOND_EVENT);

        List<Event> eventsToDelete = new ArrayList<>();
        eventsToDelete.add(model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased()));
        eventsToDelete.add(model.getFilteredEventList().get(INDEX_SECOND_EVENT.getZeroBased()));

        EDeleteCommand eDeleteCommand = new EDeleteEventCommand(
                List.of(INDEX_FIRST_EVENT, INDEX_SECOND_EVENT), List.of());

        String expectedMessage = String.format(EDeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                displayEvents(eventsToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Event p : eventsToDelete) {
            expectedModel.deleteEvent(p);
        }
        showNoEvent(expectedModel);

        assertCommandSuccess(eDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allInvalidIndexUnfilteredList_throwsCommandException() {

        EDeleteCommand eDeleteCommand = new EDeleteEventCommand(List.of(Index.fromZeroBased(100)), new ArrayList<>());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());

        assertThrows(CommandException.class,
                Messages.MESSAGE_NONE_INDEX_VALID, () -> eDeleteCommand.execute(expectedModel));
    }

    @Test
    public void execute_bothValidInvalidIndexUnfilteredList_success() {

        List<Event> eventsToDelete = new ArrayList<>();
        eventsToDelete.add(model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased()));
        eventsToDelete.add(model.getFilteredEventList().get(INDEX_SECOND_EVENT.getZeroBased()));

        EDeleteCommand eDeleteCommand = new EDeleteEventCommand(
                List.of(INDEX_FIRST_EVENT, INDEX_SECOND_EVENT), List.of("invalid", "-1"));

        String expectedMessage = String.format(EDeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS
                + "\n" + EDeleteEventCommand.MESSAGE_INVALID_EVENT_INDEX,
                displayEvents(eventsToDelete), "invalid, -1");

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Event p : eventsToDelete) {
            expectedModel.deleteEvent(p);
        }

        assertCommandSuccess(eDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        EDeleteCommand eDeleteFirstCommand = new EDeleteEventCommand(List.of(INDEX_FIRST_EVENT), List.of());
        EDeleteCommand eDeleteSecondCommand = new EDeleteEventCommand(List.of(INDEX_SECOND_EVENT), List.of());

        // same object -> returns true
        assertTrue(eDeleteFirstCommand.equals(eDeleteFirstCommand));

        // same values -> returns true
        EDeleteCommand eDeleteFirstCommandCopy = new EDeleteEventCommand(List.of(INDEX_FIRST_EVENT), List.of());
        assertTrue(eDeleteFirstCommand.equals(eDeleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(eDeleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(eDeleteFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(eDeleteFirstCommand.equals(eDeleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        assertTrue(model.getFilteredEventList().isEmpty());
    }

    /**
     * Returns list of events in the form "a, b, c,..."
     */
    private String displayEvents(List<Event> deletedEvents) {
        return deletedEvents.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }
}
