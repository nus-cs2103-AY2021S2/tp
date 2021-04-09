package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderTracker;

/**
 * A utility class containing a list of {@code Reminder} objects to be used in tests.
 */
public class TypicalReminders {

    public static final Reminder MATHS_TUITION_PAYMENT_REMINDER = new ReminderBuilder()
            .withDescription("Maths Tuition Payment Due")
            .withReminderDate("2021-06-01").build();
    public static final Reminder SCIENCE_TUITION_PAYMENT_REMINDER = new ReminderBuilder()
            .withDescription("Science Tuition Payment Due")
            .withReminderDate("2021-06-05").build();
    public static final Reminder ENGLISH_TUITION_PAYMENT_REMINDER = new ReminderBuilder()
            .withDescription("English Tuition Payment Due")
            .withReminderDate("2021-06-11").build();
    public static final Reminder LITERATURE_TUITION_PAYMENT_REMINDER = new ReminderBuilder()
            .withDescription("Literature Tuition Payment Due")
            .withReminderDate("2021-06-10").build();

    public static final Reminder PAST_REMINDER_1 = new ReminderBuilder()
            .withDescription("Maths Tuition Payment Due")
            .withReminderDate("2021-01-01").build();
    public static final Reminder PAST_REMINDER_2 = new ReminderBuilder()
            .withDescription("Science Tuition Payment Due")
            .withReminderDate("2021-01-05").build();

    private TypicalReminders() {
    } // prevents instantiation

    /**
     * Returns an {@code ReminderTracker} with all the typical reminders.
     */
    public static ReminderTracker getTypicalReminderTracker() {
        ReminderTracker rt = new ReminderTracker();
        for (Reminder reminder : getTypicalReminders()) {
            rt.addReminder(reminder);
        }
        return rt;
    }

    public static List<Reminder> getTypicalReminders() {
        return new ArrayList<>(Arrays.asList(MATHS_TUITION_PAYMENT_REMINDER, SCIENCE_TUITION_PAYMENT_REMINDER));
    }

    public static List<Reminder> getTypicalPastReminders() {
        return new ArrayList<>(Arrays.asList(PAST_REMINDER_1, PAST_REMINDER_2));
    }
}
