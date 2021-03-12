package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.testutil.TypicalItems.ELLE;
import static seedu.storemando.testutil.TypicalItems.FIONA;
import static seedu.storemando.testutil.TypicalItems.GEORGE;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.UserPrefs;
import seedu.storemando.model.item.ItemExpiringPredicate;

public class ReminderCommandTest {
    private final Model model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalStoreMando(), new UserPrefs());

    @Test
    public void equals() {
        ItemExpiringPredicate firstPredicate =
            new ItemExpiringPredicate((long) 3);
        ItemExpiringPredicate secondPredicate =
            new ItemExpiringPredicate((long) 7);

       ReminderCommand ReminderFirstCommand = new ReminderCommand(firstPredicate);
       ReminderCommand ReminderSecondCommand = new ReminderCommand(secondPredicate);

        // same object -> returns true
        assertTrue(ReminderFirstCommand.equals(ReminderFirstCommand));

        // same values -> returns true
       ReminderCommand ReminderFirstCommandCopy = new ReminderCommand(firstPredicate);
        assertTrue(ReminderFirstCommand.equals(ReminderFirstCommandCopy));

        // different types -> returns false
        assertFalse(ReminderFirstCommand.equals(1));

        // null -> returns false
        assertFalse(ReminderFirstCommand.equals(null));

        // different Item -> returns false
        assertFalse(ReminderFirstCommand.equals(ReminderSecondCommand));
    }

    @Test
    public void execute_MultipleItemsFound() {
        ItemExpiringPredicate predicate = new ItemExpiringPredicate((long) 60);
        expectedModel.updateFilteredItemList(predicate);
        assertEquals(Arrays.asList(ELLE, FIONA, GEORGE), expectedModel.getFilteredItemList());
    }

    @Test
    public void execute_noItemsFound() {
        ItemExpiringPredicate predicate = new ItemExpiringPredicate((long) 3);
        expectedModel.updateFilteredItemList(predicate);
        assertEquals(Arrays.asList(), expectedModel.getFilteredItemList());
    }

}
