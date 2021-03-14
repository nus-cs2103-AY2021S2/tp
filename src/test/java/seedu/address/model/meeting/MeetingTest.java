package seedu.address.model.meeting;

import org.junit.jupiter.api.Test;
import seedu.address.model.group.Group;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

class MeetingTest {

    private static final Name NAME = new Name("CS2103 seminar");
    private static final DateTime START = new DateTime("2021-03-11 15:04");
    private static final DateTime TERMINATE = new DateTime("2021-03-11 16:04");
    private static final Priority PRIORITY = new Priority("4");
    private static final Description DESCRIPTION = new Description("This is the time skinnychenpi write this test.");
    private static final Group GROUP = new Group("MeetingTest");
    private Set<Group> GROUPS = new HashSet<>();

    @Test
    public void isSameMeeting() {
        Meeting meeting = new Meeting(NAME, START, TERMINATE, PRIORITY, DESCRIPTION, GROUPS);
        Meeting meeting2 = new Meeting(NAME, START, TERMINATE, new Priority("1"), new Description("Test"), GROUPS);
        assertTrue(meeting.isSameMeeting(meeting2));

        Meeting meeting3 = new Meeting(NAME, new DateTime("2020-03-10 15:04"), TERMINATE, PRIORITY, DESCRIPTION, GROUPS);
        assertFalse(meeting.isSameMeeting(meeting3));
    }

    @Test
    public void isValidMeeting() {
        assertThrows(IllegalArgumentException.class, () -> new Meeting(NAME, TERMINATE, TERMINATE, PRIORITY, DESCRIPTION, GROUPS));

        // Check if start time later than terminate time will throw an error.
        assertThrows(IllegalArgumentException.class, () -> new Meeting(NAME, TERMINATE, START, PRIORITY, DESCRIPTION, GROUPS));
    }

//    @Test
//    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
//        Person person = new PersonBuilder().build();
//        assertThrows(UnsupportedOperationException.class, () -> person.getGroups().remove(0));
//    }
}
