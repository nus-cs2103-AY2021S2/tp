package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalReminders.MATHS_TUITION_PAYMENT_REMINDER;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.reminder.exceptions.DuplicateReminderException;

public class ReminderTrackerTest {

    private final ReminderTracker reminderTracker = new ReminderTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), reminderTracker.getReminderList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyReminderTracker_replacesData() {
        ReminderTracker newData = getTypicalReminderTracker();
        reminderTracker.resetData(newData);
        assertEquals(newData, reminderTracker);
    }

    @Test
    public void resetData_withDuplicateReminders_throwsDuplicateReminderException() {
        List<Reminder> newReminders = Arrays.asList(MATHS_TUITION_PAYMENT_REMINDER, MATHS_TUITION_PAYMENT_REMINDER);
        ReminderTrackerStub newData = new ReminderTrackerStub(newReminders);

        assertThrows(DuplicateReminderException.class, () -> reminderTracker.resetData(newData));
    }

    @Test
    public void hasReminder_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderTracker.hasReminder(null));
    }

    @Test
    public void hasReminder_reminderNotInReminderTracker_returnsFalse() {
        assertFalse(reminderTracker.hasReminder(MATHS_TUITION_PAYMENT_REMINDER));
    }

    @Test
    public void hasReminder_reminderInReminderTracker_returnsTrue() {
        reminderTracker.addReminder(MATHS_TUITION_PAYMENT_REMINDER);
        assertTrue(reminderTracker.hasReminder(MATHS_TUITION_PAYMENT_REMINDER));
    }

    @Test
    public void getReminderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> reminderTracker.getReminderList().remove(0));
    }

    /**
     * A stub ReadOnlyReminderTracker whose Reminder list can violate interface constraints.
     */
    private static class ReminderTrackerStub implements ReadOnlyReminderTracker {
        private final ObservableList<Reminder> reminders = FXCollections.observableArrayList();

        ReminderTrackerStub(Collection<Reminder> reminders) {
            this.reminders.setAll(reminders);
        }

        @Override
        public ObservableList<Reminder> getReminderList() {
            return reminders;
        }
    }
}
