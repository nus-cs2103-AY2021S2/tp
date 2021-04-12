package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.commons.core.Messages.MESSAGE_NO_ITEM_IN_LIST;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.storemando.logic.commands.CommandTestUtil.showEmptyListAfterFind;
import static seedu.storemando.testutil.TypicalItems.APPLE;
import static seedu.storemando.testutil.TypicalItems.BREAD;
import static seedu.storemando.testutil.TypicalItems.HEATER;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.UserPrefs;
import seedu.storemando.model.expirydate.predicate.ItemExpiringPredicate;
import seedu.storemando.model.item.comparator.ItemComparatorByExpiryDate;

public class ReminderCommandTest {
    private Model model;
    private Model expectedModel = new ModelManager(getTypicalStoreMando(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        expectedModel = new ModelManager(model.getStoreMando(), new UserPrefs());
    }

    @Test
    public void equals() {
        ItemExpiringPredicate firstPredicate = new ItemExpiringPredicate((long) 3);
        ItemExpiringPredicate secondPredicate = new ItemExpiringPredicate((long) 7);

        ReminderCommand reminderFirstCommand = new ReminderCommand(firstPredicate, 3, "days");
        ReminderCommand reminderSecondCommand = new ReminderCommand(secondPredicate, 3, "days");

        // same object -> returns true
        assertTrue(reminderFirstCommand.equals(reminderFirstCommand));

        // same values -> returns true
        ReminderCommand reminderFirstCommandCopy = new ReminderCommand(firstPredicate, 3, "days");
        assertTrue(reminderFirstCommand.equals(reminderFirstCommandCopy));

        // different types -> returns false
        assertFalse(reminderFirstCommand.equals(1));

        // null -> returns false
        assertFalse(reminderFirstCommand.equals(null));

        // different Item -> returns false
        assertFalse(reminderFirstCommand.equals(reminderSecondCommand));
    }

    @Test
    public void execute_multipleItemsFound() {
        ItemExpiringPredicate predicate = new ItemExpiringPredicate((long) 100);
        String expectedMessage = String.format(ReminderCommand.MESSAGE_SUCCESS_EXPIRING_ITEM, 100, "days");
        ReminderCommand reminderCommand = new ReminderCommand(predicate, 100L, "days");
        expectedModel.updateFilteredItemList(predicate);
        ItemComparatorByExpiryDate comparator = new ItemComparatorByExpiryDate();
        expectedModel.updateSortedItemList(comparator);
        expectedModel.setItems(expectedModel.getSortedItemList());
        assertCommandSuccess(reminderCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BREAD, APPLE), expectedModel.getFilteredItemList());
    }

    @Test
    public void getMessageTest() {
        ItemExpiringPredicate expiredPredicate = new ItemExpiringPredicate((long) -7);
        ItemExpiringPredicate expiringTodayPredicate = new ItemExpiringPredicate((long) 0);
        ItemExpiringPredicate expiringPredicate = new ItemExpiringPredicate((long) 7);

        ReminderCommand expiredReminderCommand = new ReminderCommand(expiredPredicate, -7,
            "days");
        ReminderCommand expiringTodayReminderCommand = new ReminderCommand(expiringTodayPredicate,
            0, "days");
        ReminderCommand expiringReminderCommand = new ReminderCommand(expiringPredicate,
            7, "days");

        assertEquals(expiredReminderCommand.getMessage(),
            String.format(ReminderCommand.MESSAGE_SUCCESS_EXPIRED_ITEM, 7, "days"));
        assertEquals(expiringTodayReminderCommand.getMessage(),
            String.format(ReminderCommand.MESSAGE_SUCCESS_EXPIRING_TODAY_ITEM, 0, "day"));
        assertEquals(expiringReminderCommand.getMessage(),
            String.format(ReminderCommand.MESSAGE_SUCCESS_EXPIRING_ITEM, 7, "days"));
    }

    @Test
    public void execute_noItemInList() {
        showEmptyListAfterFind(model, HEATER);
        ItemExpiringPredicate predicate = new ItemExpiringPredicate(3L);

        assertEquals(model.getFilteredItemList().size(), 0);
        assertCommandFailure(new ReminderCommand(predicate, 3L, "days"),
            model, MESSAGE_NO_ITEM_IN_LIST);
    }
}
