package seedu.address.logic.commands.deletecommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMe;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.GeneralEvent;

public class DeleteGeneralEventCommandTest {

    private Model model = new ModelManager(getTypicalRemindMe(), new UserPrefs());

    @Test
    public void execute_validEventIndex_success() {

        GeneralEvent eventToDelete = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        DeleteGeneralEventCommand deleteGeneralEventCommand = new DeleteGeneralEventCommand(INDEX_FIRST_EVENT);
        ModelManager expectedModel = new ModelManager(model.getRemindMe(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);
        String expectedMessage = String.format(DeleteGeneralEventCommand.MESSAGE_GENERAL_EVENT_SUCCESS, eventToDelete);
        assertCommandSuccess(deleteGeneralEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEventIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        DeleteGeneralEventCommand deleteGeneralEventCommand = new DeleteGeneralEventCommand(outOfBoundIndex);

        assertCommandFailure(deleteGeneralEventCommand, model, Messages.MESSAGE_INVALID_GENERAL_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteGeneralEventCommand deleteFirstCommand = new DeleteGeneralEventCommand(INDEX_FIRST_EVENT);
        DeleteGeneralEventCommand deleteSecondCommand = new DeleteGeneralEventCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteGeneralEventCommand deleteFirstCommandCopy = new DeleteGeneralEventCommand(INDEX_FIRST_EVENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
