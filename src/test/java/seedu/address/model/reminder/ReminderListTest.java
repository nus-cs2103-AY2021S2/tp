package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalReminders.MATHS_TUITION_PAYMENT_REMINDER;
import static seedu.address.testutil.TypicalReminders.SCIENCE_TUITION_PAYMENT_REMINDER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.reminder.exceptions.DuplicateReminderException;
import seedu.address.model.reminder.exceptions.ReminderNotFoundException;

public class ReminderListTest {

    private final ReminderList reminderList = new ReminderList();

    @Test
    public void contains_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderList.contains(null));
    }

    @Test
    public void contains_reminderNotInList_returnsFalse() {
        assertFalse(reminderList.contains(MATHS_TUITION_PAYMENT_REMINDER));
    }

    @Test
    public void contains_reminderInList_returnsTrue() {
        reminderList.add(MATHS_TUITION_PAYMENT_REMINDER);
        assertTrue(reminderList.contains(MATHS_TUITION_PAYMENT_REMINDER));
    }

    @Test
    public void add_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderList.add(null));
    }

    @Test
    public void add_duplicateReminder_throwsDuplicateReminderException() {
        reminderList.add(MATHS_TUITION_PAYMENT_REMINDER);
        assertThrows(DuplicateReminderException.class, () -> reminderList.add(MATHS_TUITION_PAYMENT_REMINDER));
    }

    @Test
    public void setReminder_nullTargetReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderList.setReminder(null, MATHS_TUITION_PAYMENT_REMINDER));
    }

    @Test
    public void setReminder_nullEditedReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderList.setReminder(MATHS_TUITION_PAYMENT_REMINDER, null));
    }

    @Test
    public void setReminder_targetReminderNotInList_throwsReminderNotFoundException() {
        assertThrows(ReminderNotFoundException.class, () ->
                reminderList.setReminder(MATHS_TUITION_PAYMENT_REMINDER, MATHS_TUITION_PAYMENT_REMINDER));
    }

    @Test
    public void setReminder_editedReminderIsSameReminder_success() {
        reminderList.add(MATHS_TUITION_PAYMENT_REMINDER);
        reminderList.setReminder(MATHS_TUITION_PAYMENT_REMINDER, MATHS_TUITION_PAYMENT_REMINDER);
        ReminderList expectedReminderList = new ReminderList();
        expectedReminderList.add(MATHS_TUITION_PAYMENT_REMINDER);
        assertEquals(expectedReminderList, reminderList);
    }

    @Test
    public void setReminder_editedReminderIsDifferent_success() {
        reminderList.add(MATHS_TUITION_PAYMENT_REMINDER);
        reminderList.setReminder(MATHS_TUITION_PAYMENT_REMINDER, SCIENCE_TUITION_PAYMENT_REMINDER);
        ReminderList expectedReminderList = new ReminderList();
        expectedReminderList.add(SCIENCE_TUITION_PAYMENT_REMINDER);
        assertEquals(expectedReminderList, reminderList);
    }

    @Test
    public void setReminder_duplicateReminderList_throwsDuplicateReminderException() {
        reminderList.add(MATHS_TUITION_PAYMENT_REMINDER);
        reminderList.add(SCIENCE_TUITION_PAYMENT_REMINDER);
        assertThrows(DuplicateReminderException.class, () ->
                reminderList.setReminder(MATHS_TUITION_PAYMENT_REMINDER, SCIENCE_TUITION_PAYMENT_REMINDER));
    }

    @Test
    public void remove_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderList.remove(null));
    }

    @Test
    public void remove_reminderDoesNotExist_throwsReminderNotFoundException() {
        assertThrows(ReminderNotFoundException.class, () -> reminderList.remove(MATHS_TUITION_PAYMENT_REMINDER));
    }

    @Test
    public void remove_existingReminder_removesReminder() {
        reminderList.add(MATHS_TUITION_PAYMENT_REMINDER);
        reminderList.remove(MATHS_TUITION_PAYMENT_REMINDER);
        ReminderList expectedReminderList = new ReminderList();
        assertEquals(expectedReminderList, reminderList);
    }

    @Test
    public void setReminders_nullReminderList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderList.setReminders((ReminderList) null));
    }

    @Test
    public void setReminders_reminderList_replacesOwnListWithProvidedreminderList() {
        reminderList.add(MATHS_TUITION_PAYMENT_REMINDER);
        ReminderList expectedReminderList = new ReminderList();
        expectedReminderList.add(SCIENCE_TUITION_PAYMENT_REMINDER);
        reminderList.setReminders(expectedReminderList);
        assertEquals(expectedReminderList, reminderList);
    }

    @Test
    public void setReminders_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reminderList.setReminders((List<Reminder>) null));
    }

    @Test
    public void setReminders_list_replacesOwnListWithProvidedList() {
        reminderList.add(MATHS_TUITION_PAYMENT_REMINDER);
        List<Reminder> anotherReminderList = Collections.singletonList(SCIENCE_TUITION_PAYMENT_REMINDER);
        reminderList.setReminders(anotherReminderList);
        ReminderList expectedReminderList = new ReminderList();
        expectedReminderList.add(SCIENCE_TUITION_PAYMENT_REMINDER);
        assertEquals(expectedReminderList, reminderList);
    }

    @Test
    public void setReminders_listWithDuplicateReminders_throwsDuplicateReminderException() {
        List<Reminder> listWithDuplicateReminders = Arrays.asList(MATHS_TUITION_PAYMENT_REMINDER,
                MATHS_TUITION_PAYMENT_REMINDER);
        assertThrows(DuplicateReminderException.class, () -> reminderList.setReminders(listWithDuplicateReminders));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                reminderList.asUnmodifiableObservableList().remove(0));
    }
}
