package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class MeetingTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Meeting(null));
    }

    @Test
    public void constructor_invalidMeeting_throwsIllegalArgumentException() {
        String invalidMeeting = "";
        assertThrows(IllegalArgumentException.class, () -> new Meeting(invalidMeeting));
    }

    @Test
    void isValidMeeting() {
        assertThrows(NullPointerException.class, () -> Meeting.isValidMeeting(null));

        assertFalse(Meeting.isValidMeeting("")); // empty string
        assertFalse(Meeting.isValidMeeting(" ")); // spaces only

        // missing parts
        assertFalse(Meeting.isValidMeeting(" @ 2020-04-01 18:00")); // missing local part
        assertFalse(Meeting.isValidMeeting("Prank2020-04-01 18:00")); // missing '@' symbol
        assertFalse(Meeting.isValidMeeting("Prank @ ")); // missing datetime

        // invalid parts
        assertFalse(Meeting.isValidMeeting("Prank @ Tomorrow")); // not a datetime input
        assertFalse(Meeting.isValidMeeting("Prank @ 2020/04/01 18:00")); // wrong datetime format
        assertFalse(Meeting.isValidMeeting("Prank @ 2020-4-1 6:00")); // missing digits in datetime
        assertFalse(Meeting.isValidMeeting("Prank@ 2020-04-01 18:00")); // no space before @
        assertFalse(Meeting.isValidMeeting("Prank @2020-04-01 18:00")); // no space after @
        assertFalse(Meeting.isValidMeeting("Prank @ 2020-04-01 18:00  ")); // trailing space
        assertFalse(Meeting.isValidMeeting("Prank @@ 2020-04-01 18:00  ")); // double '@' symbol

        // valid meeting
        assertTrue(Meeting.isValidMeeting("Prank @ 2020-04-01 18:00"));
        assertTrue(Meeting.isValidMeeting("Something @ 2021-03-06 12:45"));
    }

    @Test
    void generateDateTime() {
        String invalidDateTime = "";
        assertThrows(NullPointerException.class, () -> Meeting.generateDateTime(null, null));
        assertThrows(IllegalArgumentException.class, () ->
                Meeting.generateDateTime("2020-18-56 12:34", invalidDateTime));
        assertThrows(IllegalArgumentException.class, () ->
                Meeting.generateDateTime("2020-02-28 70:34", invalidDateTime));

        assertEquals("2020-04-01T18:00", Meeting.generateDateTime("2020-04-01 18:00", invalidDateTime).toString());
        assertEquals("2020-02-28T12:45", Meeting.generateDateTime("2020-02-28 12:45", invalidDateTime).toString());
        assertEquals("2020-02-29T12:45", Meeting.generateDateTime("2020-02-31 12:45", invalidDateTime).toString());
    }
}
