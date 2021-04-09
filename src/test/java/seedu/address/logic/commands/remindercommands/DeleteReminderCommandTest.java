package seedu.address.logic.commands.remindercommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showReminderAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModel.ModelType.REMINDERTRACKER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.reminder.Reminder;
import seedu.address.testutil.TypicalModel;

public class DeleteReminderCommandTest {

    private final Model model = TypicalModel.getTypicalModel();

    @Test
    public void execute_invalidDeleteIndex() {
        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(Index.fromOneBased(1000000));

        assertCommandFailure(deleteReminderCommand, model, MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Reminder reminderToDelete = model.getFilteredReminderList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteReminderCommand deleteCommand = new DeleteReminderCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteReminderCommand.MESSAGE_DELETE_REMINDER_SUCCESS, reminderToDelete);

        ModelManager expectedModel = TypicalModel.getTypicalModel(model, REMINDERTRACKER);
        expectedModel.deleteReminder(reminderToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReminderList().size() + 1);
        DeleteReminderCommand deleteCommand = new DeleteReminderCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showReminderAtIndex(model, INDEX_FIRST_PERSON);

        Reminder reminderToDelete = model.getFilteredReminderList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteReminderCommand deleteCommand = new DeleteReminderCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteReminderCommand.MESSAGE_DELETE_REMINDER_SUCCESS, reminderToDelete);

        Model expectedModel = TypicalModel.getTypicalModel(model, REMINDERTRACKER);
        expectedModel.deleteReminder(reminderToDelete);
        showNoReminder(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showReminderAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of reminder tracker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getReminderTracker().getReminderList().size());

        DeleteReminderCommand deleteCommand = new DeleteReminderCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteReminderCommand deleteFirstCommand = new DeleteReminderCommand(INDEX_FIRST_PERSON);
        DeleteReminderCommand deleteSecondCommand = new DeleteReminderCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteReminderCommand deleteFirstCommandCopy = new DeleteReminderCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no reminders.
     */
    private void showNoReminder(Model model) {
        model.updateFilteredReminderList(p -> false);

        assertTrue(model.getFilteredReminderList().isEmpty());
    }
}
