package seedu.cakecollate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.commons.core.Messages.MESSAGE_ORDERS_REMINDER_OVERVIEW;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.testutil.TypicalOrders.ALICE;
import static seedu.cakecollate.testutil.TypicalOrders.BENSON;
import static seedu.cakecollate.testutil.TypicalOrders.CARL;
import static seedu.cakecollate.testutil.TypicalOrders.DANIEL;
import static seedu.cakecollate.testutil.TypicalOrders.ELLE;
import static seedu.cakecollate.testutil.TypicalOrders.FIONA;
import static seedu.cakecollate.testutil.TypicalOrders.GEORGE;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import java.util.Arrays;
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

    @Test
    public void execute_largeRange_allOrdersFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_REMINDER_OVERVIEW, 7, 69420);
        System.out.println(expectedMessage);
        ReminderDatePredicate predicate = new ReminderDatePredicate(69420);
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, FIONA, GEORGE, ELLE),
                model.getFilteredOrderList());
    }

    // This JUnit test will fail if the date of testing is 138 days after 2/4/2021.
    @Test
    public void execute_largeRange_mostOrdersFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_REMINDER_OVERVIEW, 6, 500);
        System.out.println(expectedMessage);
        ReminderDatePredicate predicate = new ReminderDatePredicate(500);
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, FIONA, GEORGE),
                model.getFilteredOrderList());
    }
}


