package seedu.address.logic.commands.remindercommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModel.ModelType.REMINDERTRACKER;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;
import seedu.address.testutil.ReminderBuilder;
import seedu.address.testutil.TypicalModel;

/**
 * Contains integration tests (interaction with the Model) for {@code AddReminderCommand}.
 */
public class AddReminderCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = TypicalModel.getTypicalModel();
    }

    @Test
    public void execute_newReminder_success() {
        Reminder validReminder = new ReminderBuilder().build();

        Model expectedModel = TypicalModel.getTypicalModel(model, REMINDERTRACKER);

        expectedModel.addReminder(validReminder);

        assertCommandSuccess(new AddReminderCommand(validReminder), model,
                String.format(AddReminderCommand.MESSAGE_SUCCESS, validReminder), expectedModel);
    }

    @Test
    public void execute_duplicateReminder_throwsCommandException() {
        Reminder scheduleInList = model.getReminderTracker().getReminderList().get(0);
        assertCommandFailure(new AddReminderCommand(scheduleInList), model,
                AddReminderCommand.MESSAGE_DUPLICATE_REMINDER);
    }
}
