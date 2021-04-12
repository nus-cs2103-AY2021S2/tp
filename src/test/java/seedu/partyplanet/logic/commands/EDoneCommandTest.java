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

import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EDoneCommand}.
 */
public class EDoneCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void execute_validSingleIndexUnfilteredList_success() {
        Event eventToComplete = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EDoneCommand eDoneCommand = new EDoneCommand(List.of(INDEX_FIRST_EVENT), List.of());

        String expectedMessage = String.format(EDoneCommand.MESSAGE_EVENT_DONE_SUCCESS, eventToComplete.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        expectedModel.setEvent(eventToComplete, eventToComplete.setDone());

        assertCommandSuccess(eDoneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventToComplete = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EDoneCommand eDoneCommand = new EDoneCommand(List.of(INDEX_FIRST_EVENT), List.of());

        String expectedMessage = String.format(EDoneCommand.MESSAGE_EVENT_DONE_SUCCESS, eventToComplete.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        showEventAtIndex(expectedModel, INDEX_FIRST_EVENT);
        expectedModel.setEvent(eventToComplete, eventToComplete.setDone());

        assertCommandSuccess(eDoneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndexUnfilteredList_success() {

        List<Event> eventsToComplete = new ArrayList<>();
        eventsToComplete.add(model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased()));
        eventsToComplete.add(model.getFilteredEventList().get(INDEX_SECOND_EVENT.getZeroBased()));

        EDoneCommand eDoneCommand = new EDoneCommand(
                List.of(INDEX_FIRST_EVENT, INDEX_SECOND_EVENT), List.of());

        String expectedMessage =
                String.format(EDoneCommand.MESSAGE_EVENT_DONE_SUCCESS, displayEvents(eventsToComplete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Event e : eventsToComplete) {
            expectedModel.setEvent(e, e.setDone());
        }

        assertCommandSuccess(eDoneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndexFilteredList_success() {
        showEventAtMultipleIndex(model, INDEX_FIRST_EVENT, INDEX_SECOND_EVENT);

        List<Event> eventsToComplete = new ArrayList<>();
        eventsToComplete.add(model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased()));
        eventsToComplete.add(model.getFilteredEventList().get(INDEX_SECOND_EVENT.getZeroBased()));

        EDoneCommand eDoneCommand = new EDoneCommand(
                List.of(INDEX_FIRST_EVENT, INDEX_SECOND_EVENT), List.of());

        String expectedMessage =
                String.format(EDoneCommand.MESSAGE_EVENT_DONE_SUCCESS, displayEvents(eventsToComplete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        showEventAtMultipleIndex(expectedModel, INDEX_FIRST_EVENT, INDEX_SECOND_EVENT);
        for (Event e : eventsToComplete) {
            expectedModel.setEvent(e, e.setDone());
        }

        assertCommandSuccess(eDoneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allInvalidIndexUnfilteredList_throwsCommandException() {

        EDoneCommand eDoneCommand = new EDoneCommand(List.of(Index.fromZeroBased(100)), new ArrayList<>());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());

        assertThrows(CommandException.class,
                EDoneCommand.MESSAGE_NONE_EVENT_INDEX_VALID, () -> eDoneCommand.execute(expectedModel));
    }

    @Test
    public void execute_alreadyCompletedIndexUnfilteredList_throwsCommandException() {
        // Model with first index already done
        ModelManager modelCopy = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        Event eventToComplete = modelCopy.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        modelCopy.setEvent(eventToComplete, eventToComplete.setDone());

        EDoneCommand eDoneCommand = new EDoneCommand(List.of(INDEX_FIRST_EVENT), new ArrayList<>());

        assertThrows(CommandException.class,
                EDoneCommand.MESSAGE_NONE_EVENT_INDEX_VALID, () -> eDoneCommand.execute(modelCopy));
    }

    @Test
    public void execute_bothValidInvalidIndexUnfilteredList_success() {

        List<Event> eventsToComplete = new ArrayList<>();
        eventsToComplete.add(model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased()));
        eventsToComplete.add(model.getFilteredEventList().get(INDEX_SECOND_EVENT.getZeroBased()));

        EDoneCommand eDoneCommand = new EDoneCommand(
                List.of(INDEX_FIRST_EVENT, INDEX_SECOND_EVENT), List.of("invalid", "-1"));

        String expectedMessage = String.format(EDoneCommand.MESSAGE_EVENT_DONE_SUCCESS
                + "\n" + EDoneCommand.MESSAGE_INVALID_EVENT_INDEX,
                displayEvents(eventsToComplete),
                "invalid, -1");

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Event e : eventsToComplete) {
            expectedModel.setEvent(e, e.setDone());
        }

        assertCommandSuccess(eDoneCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void equals() {
        EDoneCommand completeFirstCommand = new EDoneCommand(List.of(INDEX_FIRST_EVENT), List.of());
        EDoneCommand completeSecondCommand = new EDoneCommand(List.of(INDEX_SECOND_EVENT), List.of());
        EDoneCommand completeFirstWithInvalidCommand = new EDoneCommand(List.of(INDEX_FIRST_EVENT), List.of("-1"));

        // same object -> returns true
        assertTrue(completeFirstCommand.equals(completeFirstCommand));

        // same values -> returns true
        EDoneCommand completeFirstCommandCopy = new EDoneCommand(List.of(INDEX_FIRST_EVENT), List.of());
        assertTrue(completeFirstCommand.equals(completeFirstCommandCopy));

        // different types -> returns false
        assertFalse(completeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(completeFirstCommand.equals(null));

        // different target indexes -> returns false
        assertFalse(completeFirstCommand.equals(completeSecondCommand));

        // different invalid indexes -> returns false
        assertFalse(completeFirstCommand.equals(completeFirstWithInvalidCommand));
    }

    /**
     * Returns list of events in the form "a, b, c,..."
     */
    private String displayEvents(List<Event> events) {
        return events.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }
}
