package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.Group;


class MeetingTest {

    private static final MeetingName MEETING_NAME = new MeetingName("CS2103 seminar");
    private static final DateTime START = new DateTime("2021-03-11 15:04");
    private static final DateTime TERMINATE = new DateTime("2021-03-11 16:04");
    private static final Priority PRIORITY = new Priority("4");
    private static final Description DESCRIPTION = new Description("This is the time skinnychenpi write this test.");
    private static final Group GROUP = new Group("MeetingTest");
    private Set<Group> groups = new HashSet<>();

    @Test
    public void isSameMeeting() {
        Meeting meeting = new Meeting(MEETING_NAME, START, TERMINATE, PRIORITY, DESCRIPTION, groups);
        Meeting meeting2 = new Meeting(MEETING_NAME, START, TERMINATE, new Priority("1"),
                new Description("Test"), groups);
        assertTrue(meeting.isSameMeeting(meeting2));

        Meeting meeting3 = new Meeting(MEETING_NAME, new DateTime("2020-03-10 15:04"),
                TERMINATE, PRIORITY, DESCRIPTION, groups);
        assertFalse(meeting.isSameMeeting(meeting3));
    }

    @Test
    public void isValidMeeting() {
        assertThrows(IllegalArgumentException.class, () -> new Meeting(MEETING_NAME, TERMINATE, TERMINATE,
                PRIORITY, DESCRIPTION, groups));

        // Check if start time later than terminate time will throw an error.
        assertThrows(IllegalArgumentException.class, () -> new Meeting(MEETING_NAME, TERMINATE, START,
                PRIORITY, DESCRIPTION, groups));
    }

    //    @Test
    //    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
    //        Person person = new PersonBuilder().build();
    //        assertThrows(UnsupportedOperationException.class, () -> person.getGroups().remove(0));
    //    }
}
