package seedu.address.logic.commands.remindercommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNABLE_TO_EDIT_PAST_REMINDER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REMINDER_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REMINDER_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DATE_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DESC_THREE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showReminderAtIndex;
import static seedu.address.logic.commands.remindercommands.EditReminderCommand.MESSAGE_DUPLICATE_REMINDER;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModel.ModelType.REMINDERTRACKER;
import static seedu.address.testutil.TypicalReminders.getTypicalPastReminders;
import static seedu.address.testutil.TypicalReminders.getTypicalReminders;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.remindercommands.EditReminderCommand.EditReminderDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderTracker;
import seedu.address.testutil.EditReminderDescriptorBuilder;
import seedu.address.testutil.ReminderBuilder;
import seedu.address.testutil.TypicalModel;

public class EditReminderCommandTest {

    private final Model model = TypicalModel.getTypicalModel();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Reminder editedReminder = new ReminderBuilder().build();
        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder(editedReminder).build();
        EditReminderCommand editCommand = new EditReminderCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditReminderCommand.MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder);

        Model expectedModel = TypicalModel.getTypicalModel(model, REMINDERTRACKER);
        expectedModel.setReminder(model.getFilteredReminderList().get(0), editedReminder);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredReminderList().size());
        Reminder lastReminder = model.getFilteredReminderList().get(indexLastPerson.getZeroBased());

        ReminderBuilder reminderInList = new ReminderBuilder(lastReminder);
        Reminder editedReminder = reminderInList.withDescription(VALID_REMINDER_DESC_THREE)
                .withReminderDate(VALID_REMINDER_DATE_THREE).build();

        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder()
                .withDescription(VALID_REMINDER_DESC_THREE).withReminderDate(VALID_REMINDER_DATE_THREE).build();
        EditReminderCommand editCommand = new EditReminderCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditReminderCommand.MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder);

        Model expectedModel = TypicalModel.getTypicalModel(model, REMINDERTRACKER);
        expectedModel.setReminder(lastReminder, editedReminder);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditReminderCommand editCommand = new EditReminderCommand(INDEX_FIRST_PERSON, new EditReminderDescriptor());
        Reminder editedReminder = model.getFilteredReminderList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditReminderCommand.MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder);

        Model expectedModel = TypicalModel.getTypicalModel(model, REMINDERTRACKER);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showReminderAtIndex(model, INDEX_FIRST_PERSON);

        Reminder reminderInFilteredList = model.getFilteredReminderList().get(INDEX_FIRST_PERSON.getZeroBased());
        Reminder editedReminder = new ReminderBuilder(reminderInFilteredList)
                .withDescription(VALID_REMINDER_DESC_THREE).withReminderDate(VALID_REMINDER_DATE_THREE).build();

        EditReminderCommand editCommand = new EditReminderCommand(INDEX_FIRST_PERSON,
                new EditReminderDescriptorBuilder().withDescription(VALID_REMINDER_DESC_THREE)
                        .withReminderDate(VALID_REMINDER_DATE_THREE).build());

        String expectedMessage = String.format(EditReminderCommand.MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder);

        Model expectedModel = TypicalModel.getTypicalModel(model, REMINDERTRACKER);
        expectedModel.setReminder(model.getFilteredReminderList().get(0), editedReminder);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateReminderFilteredList_failure() {
        showReminderAtIndex(model, INDEX_FIRST_PERSON);

        // edit reminder in filtered list into a duplicate in reminder tracker
        Reminder reminderInList = model.getReminderTracker().getReminderList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditReminderCommand editCommand = new EditReminderCommand(INDEX_FIRST_PERSON,
                new EditReminderDescriptorBuilder(reminderInList).build());

        assertCommandFailure(editCommand, model, MESSAGE_DUPLICATE_REMINDER);
    }

    @Test
    public void execute_editedPastReminder_failure() {
        ReminderTracker reminderTracker = new ReminderTracker();
        reminderTracker.setReminders(getTypicalPastReminders());
        Model pastModel = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), getTypicalBudgetBook(), getTypicalGradeBook(),
                getTypicalScheduleTracker(), reminderTracker);

        Reminder reminderInList = pastModel.getReminderTracker().getReminderList()
                .get(INDEX_SECOND_PERSON.getZeroBased());
        EditReminderCommand editCommand = new EditReminderCommand(INDEX_FIRST_PERSON,
                new EditReminderDescriptorBuilder(reminderInList).build());

        assertCommandFailure(editCommand, pastModel, MESSAGE_UNABLE_TO_EDIT_PAST_REMINDER);
    }

    @Test
    public void execute_invalidIndexOutOfBounds_failure() {
        List<Reminder> reminderList = getTypicalReminders();
        Reminder reminder1 = reminderList.get(0);
        EditReminderDescriptor editReminderDescriptor = new EditReminderDescriptorBuilder(reminder1).build();

        EditReminderCommand editReminderCommand = new EditReminderCommand(Index.fromOneBased(1000000),
                editReminderDescriptor);

        assertCommandFailure(editReminderCommand, model, MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditReminderCommand standardCommand = new EditReminderCommand(INDEX_FIRST_PERSON, DESC_REMINDER_MATHS);

        // same values -> returns true
        EditReminderDescriptor copyDescriptor = new EditReminderDescriptor(DESC_REMINDER_MATHS);
        EditReminderCommand commandWithSameValues = new EditReminderCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListReminderCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditReminderCommand(INDEX_SECOND_PERSON, DESC_REMINDER_MATHS)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditReminderCommand(INDEX_FIRST_PERSON, DESC_REMINDER_SCIENCE)));
    }
}
