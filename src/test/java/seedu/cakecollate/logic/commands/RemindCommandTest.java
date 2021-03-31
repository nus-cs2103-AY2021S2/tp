package seedu.cakecollate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.commons.core.Messages.MESSAGE_ORDERS_REMINDER_OVERVIEW;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;
import seedu.cakecollate.model.UserPrefs;
import seedu.cakecollate.model.order.ReminderDatePredicate;
import seedu.cakecollate.testutil.TypicalOrderItems;

/**
 * Contains integration tests (interaction with the Model) for {@code RemindCommand}.
 */
public class RemindCommandTest {
    private Model model = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
            TypicalOrderItems.getTypicalOrderItemsModel());
    private Model expectedModel = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
            TypicalOrderItems.getTypicalOrderItemsModel());

    @Test
    public void equals() {
        ReminderDatePredicate firstPredicate = new ReminderDatePredicate(1);
        ReminderDatePredicate secondPredicate = new ReminderDatePredicate(2);

        RemindCommand remindFirstCommand = new RemindCommand(firstPredicate);
        RemindCommand remindSecondCommand = new RemindCommand(secondPredicate);

        // same object return true
        assertTrue(remindFirstCommand.equals(remindFirstCommand));

        // same values return true
        RemindCommand copy = new RemindCommand(firstPredicate);
        assertTrue(remindFirstCommand.equals(copy));

        // different types return false
        assertFalse(remindFirstCommand.equals("1"));

        // null -> returns false
        assertFalse(remindFirstCommand.equals(null));

        // different predicate returns false
        assertFalse(remindFirstCommand.equals(remindSecondCommand));
    }

    @Test
    public void execute_invalidRange_noOrderFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_REMINDER_OVERVIEW, 0, -1);
        ReminderDatePredicate predicate = new ReminderDatePredicate(-1);
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredOrderList());
    }

    @Test
    public void execute_smallRange_noOrderFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_REMINDER_OVERVIEW, 0, 10);
        ReminderDatePredicate predicate = new ReminderDatePredicate(10);
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredOrderList());
    }
}


