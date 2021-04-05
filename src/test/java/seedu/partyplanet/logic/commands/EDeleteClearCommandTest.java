package seedu.partyplanet.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EDeleteClearCommand}.
 */
public class EDeleteClearCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void execute_validDeleteClear_success() {
        EDeleteCommand eDeleteCommand = new EDeleteClearCommand();

        List<Event> eventsDeleted = model.getFilteredEventList();

        String expectedMessage = String.format(EDeleteClearCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                displayEvents(eventsDeleted));
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Event p : eventsDeleted) {
            expectedModel.deleteEvent(p);
        }

        assertCommandSuccess(eDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteClearEmptyList_success() {
        EDeleteCommand eDeleteCommand = new EDeleteClearCommand();

        model.updateFilteredEventList(p -> false);

        String expectedMessage = String.format(EDeleteClearCommand.MESSAGE_DELETE_EVENT_SUCCESS, "None!");
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        expectedModel.updateFilteredEventList(p -> false);

        assertCommandSuccess(eDeleteCommand, model, expectedMessage, expectedModel);
    }



    @Test
    public void equals() {
        EDeleteCommand eDeleteFirstCommand = new EDeleteClearCommand();
        EDeleteCommand eDeleteSecondCommand = new EDeleteClearCommand();

        // same object -> returns true
        assertTrue(eDeleteFirstCommand.equals(eDeleteFirstCommand));

        // same values -> returns true
        assertTrue(eDeleteFirstCommand.equals(eDeleteSecondCommand));

        // different types -> returns false
        assertFalse(eDeleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(eDeleteFirstCommand.equals(null));
    }

    /**
     * Returns list of events in the form "a, b, c,..."
     */
    private String displayEvents(List<Event> deletedEvents) {
        return deletedEvents.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .orElse("None!");
    }
}
