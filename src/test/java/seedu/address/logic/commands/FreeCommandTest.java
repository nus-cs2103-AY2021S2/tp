package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.CLASS_MEETING;
import static seedu.address.testutil.TypicalEntries.CONSULTATION;
import static seedu.address.testutil.TypicalEntries.getTypicalEntriesList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.ListOccupyingEntryPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FreeCommand}.
 */
public class FreeCommandTest {
    private Model model = new ModelManager(getTypicalEntriesList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalEntriesList(), new UserPrefs());

    @Test
    public void equals() {
        EntryDate firstStartDate = new EntryDate("2022-02-01 13:00");
        EntryDate firstEndDate = new EntryDate("2022-02-01 15:30");
        EntryDate secondStartDate = new EntryDate("2022-02-02 14:00");
        EntryDate secondEndDate = new EntryDate("2022-02-02 15:00");
        ListOccupyingEntryPredicate firstPredicate =
                new ListOccupyingEntryPredicate(firstStartDate, firstEndDate);
        ListOccupyingEntryPredicate secondPredicate =
                new ListOccupyingEntryPredicate(secondStartDate, secondEndDate);

        FreeCommand freeFirstCommand = new FreeCommand(firstPredicate);
        FreeCommand freeSecondCommand = new FreeCommand(secondPredicate);

        // same object -> returns true
        assertTrue(freeFirstCommand.equals(freeFirstCommand));

        // same values -> returns true
        FreeCommand freeFirstCommandCopy = new FreeCommand(firstPredicate);
        assertTrue(freeFirstCommand.equals(freeFirstCommandCopy));

        // different types -> returns false
        assertFalse(freeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(freeFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(freeFirstCommand.equals(freeSecondCommand));
    }

    @Test
    public void execute_intervalNotOccupied_freeMessageShown() {
        String expectedMessage = FreeCommand.MESSAGE_FREE;
        ListOccupyingEntryPredicate predicate = preparePredicate("2022-02-03 13:00", "2022-02-03 15:30");
        FreeCommand command = new FreeCommand(predicate);
        expectedModel.updateFilteredEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEntryList());
    }

    @Test
    public void execute_intervalOccupied_notFreeMessageShown() {
        String expectedMessage = FreeCommand.MESSAGE_NOT_FREE;
        ListOccupyingEntryPredicate predicate = preparePredicate("2022-02-01 13:00", "2022-02-01 15:30");
        FreeCommand command = new FreeCommand(predicate);
        expectedModel.updateFilteredEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CONSULTATION, CLASS_MEETING), model.getFilteredEntryList());
    }

    /**
     * Parses {@code userInput} into a {@code ListOccupyingEntryPredicate}.
     */
    private ListOccupyingEntryPredicate preparePredicate(String startDate, String endDate) {
        EntryDate start = new EntryDate(startDate);
        EntryDate end = new EntryDate(endDate);
        return new ListOccupyingEntryPredicate(start, end);
    }
}
