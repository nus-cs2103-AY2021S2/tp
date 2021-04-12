package seedu.address.logic.commands.schedulecommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showScheduleAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModel.ModelType.SCHEDULETRACKER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.TypicalModel;

public class DeleteScheduleCommandTest {

    private final Model model = TypicalModel.getTypicalModel();

    @Test
    public void execute_invalidDeleteIndex() {
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(Index.fromOneBased(1000000));

        assertCommandFailure(deleteScheduleCommand, model, MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Schedule scheduleToDelete = model.getFilteredScheduleList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteScheduleCommand deleteCommand = new DeleteScheduleCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteScheduleCommand.MESSAGE_DELETE_SCHEDULE_SUCCESS, scheduleToDelete);

        ModelManager expectedModel = TypicalModel.getTypicalModel(model, SCHEDULETRACKER);
        expectedModel.deleteSchedule(scheduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        DeleteScheduleCommand deleteCommand = new DeleteScheduleCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showScheduleAtIndex(model, INDEX_FIRST_PERSON);

        Schedule scheduleToDelete = model.getFilteredScheduleList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteScheduleCommand deleteCommand = new DeleteScheduleCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteScheduleCommand.MESSAGE_DELETE_SCHEDULE_SUCCESS, scheduleToDelete);

        Model expectedModel = TypicalModel.getTypicalModel(model, SCHEDULETRACKER);
        expectedModel.deleteSchedule(scheduleToDelete);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULE);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showScheduleAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of schedule tracker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getScheduleTracker().getScheduleList().size());

        DeleteScheduleCommand deleteCommand = new DeleteScheduleCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteScheduleCommand deleteFirstCommand = new DeleteScheduleCommand(INDEX_FIRST_PERSON);
        DeleteScheduleCommand deleteSecondCommand = new DeleteScheduleCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteScheduleCommand deleteFirstCommandCopy = new DeleteScheduleCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no schedules.
     */
    private void showNoSchedule(Model model) {
        model.updateFilteredScheduleList(p -> false);

        assertTrue(model.getFilteredScheduleList().isEmpty());
    }
}
