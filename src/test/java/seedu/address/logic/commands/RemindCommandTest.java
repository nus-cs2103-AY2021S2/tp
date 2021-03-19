package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_REMINDER_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ReminderDatePredicate;
/**
 * Contains integration tests (interaction with the Model) for {@code RemindCommand}.
 */
public class RemindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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
        String expectedMessage = String.format(MESSAGE_PERSONS_REMINDER_OVERVIEW, 0, -1);
        ReminderDatePredicate predicate = new ReminderDatePredicate(-1);
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_smallRange_noOrderFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_REMINDER_OVERVIEW, 0, 10);
        ReminderDatePredicate predicate = new ReminderDatePredicate(10);
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }
}


