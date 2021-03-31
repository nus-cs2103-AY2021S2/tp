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
            .withDescription("2021-04-01").build();
    public static final Reminder SCIENCE_TUITION_PAYMENT_REMINDER = new ReminderBuilder()
            .withDescription("Science Tuition Payment Due")
            .withDescription("2021-04-05").build();

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
}
