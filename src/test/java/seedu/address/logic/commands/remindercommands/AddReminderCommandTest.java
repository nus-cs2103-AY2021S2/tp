package seedu.address.logic.commands.remindercommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.remindercommands.AddReminderCommand.MESSAGE_DUPLICATE_REMINDER;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;
import seedu.address.testutil.ReminderBuilder;
import seedu.address.testutil.TypicalModel;


public class AddReminderCommandTest {

    private final Model model = TypicalModel.getTypicalModel();
    private final Reminder firstReminder = model.getReminderTracker().getReminderList().get(0);

    @Test
    public void execute_alreadyHasReminder() {
        AddReminderCommand addReminderCommand = new AddReminderCommand(firstReminder);
        System.out.println(firstReminder);
        assertCommandFailure(addReminderCommand, model, MESSAGE_DUPLICATE_REMINDER);
    }

    @Test
    public void execute_pastDateReminder() {
        Reminder invalidReminder = new ReminderBuilder(firstReminder)
                .withReminderDate("2021-01-24").build();

        AddReminderCommand addReminderCommand = new AddReminderCommand(invalidReminder);

        assertCommandFailure(addReminderCommand, model, MESSAGE_INVALID_DATE);
    }

    @Test
    public void equals() {
        Reminder reminder1 = new ReminderBuilder().withDescription(VALID_REMINDER_DESC_ONE).build();
        Reminder reminder2 = new ReminderBuilder().withDescription(VALID_REMINDER_DESC_TWO).build();
        AddReminderCommand addReminder1Command = new AddReminderCommand(reminder1);
        AddReminderCommand addReminder2Command = new AddReminderCommand(reminder2);

        // same object -> returns true
        assertEquals(addReminder1Command, addReminder1Command);

        // same values -> returns true
        AddReminderCommand addReminder1CommandCopy = new AddReminderCommand(reminder1);
        assertEquals(addReminder1CommandCopy, addReminder1Command);

        // different types -> returns false
        assertNotEquals(addReminder1Command, 1);

        // null -> returns false
        assertNotEquals(addReminder1Command, null);

        // different person -> returns false
        assertNotEquals(addReminder1Command, addReminder2Command);
    }
}
