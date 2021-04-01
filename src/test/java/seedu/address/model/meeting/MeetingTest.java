package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MeetingTest {

    private static final String INVALID_MEETING = "23.12.2021/1830/2000/KENT RIDGE MRT";
    private static final String INVALID_DATE = "23/12/2021";
    private static final String INVALID_START = "1830";
    private static final String INVALID_END = "25:00";
    private static final String INVALID_PLACE = "      ABC      ";

    private static final String VALID_MEETING = "23.12.2021 18:30 20:00 KENT RIDGE MRT";
    private static final String VALID_DATE = "23.12.2021";
    private static final String VALID_START = "18:30";
    private static final String VALID_END = "20:00";
    private static final String VALID_PLACE = "KENT RIDGE MRT";

    @Test
    public void constructor_newMeeting_equal() {
        assertEquals(new Meeting(VALID_DATE, VALID_START, VALID_END, VALID_PLACE),
                Meeting.newMeeting(VALID_MEETING));
    }

    @Test
    public void isValidMeeting_true() {
        // valid meeting
        assertTrue(Meeting.isValidMeeting(VALID_MEETING));

        // valid place date time
        assertTrue(Meeting.isValidMeeting(VALID_DATE, VALID_START, VALID_END, VALID_PLACE));
    }

    @Test
    public void isValidMeeting_false() {
        // invalid meeting
        assertFalse(Meeting.isValidMeeting(INVALID_MEETING));

        // invalid date
        assertFalse(Meeting.isValidMeeting(INVALID_DATE, VALID_START, VALID_END, VALID_PLACE));

        // invalid time
        assertFalse(Meeting.isValidMeeting(VALID_DATE, INVALID_START, INVALID_END, VALID_PLACE));

        // invalid start end
        assertFalse(Meeting.isValidMeeting(VALID_DATE, VALID_END, VALID_START, VALID_PLACE));

        // invalid place
        assertFalse(Meeting.isValidMeeting(VALID_DATE, VALID_START, VALID_END, INVALID_PLACE));
    }
}
