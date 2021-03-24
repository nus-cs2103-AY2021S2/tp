package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;
import seedu.address.testutil.Assert;

class ReminderTest {

    Reminder sampleReminder = new Reminder(new MeetingName("meeting"),
            new DateTime("2030-06-24 11:30"),
            new Priority("2"));

    @Test
    void getMeetingName() {
        MeetingName name = sampleReminder.getMeetingName();
        assertEquals(name.fullName, "meeting");
    }

    @Test
    void getStartDate() {
    }

    @Test
    void getPriority() {
    }

    @Test
    void updateTimeAndDaysUntil() {
    }

    @Test
    void getTimeUntil() {
        String out = sampleReminder.getTimeUntil();
        assertTrue(out.contains("years"));
        assertTrue(out.contains("months"));
        assertTrue(out.contains("days"));
        assertTrue(out.contains("hours"));
        assertTrue(out.contains("minutes"));
        assertTrue(out.contains("seconds"));
    }
}
