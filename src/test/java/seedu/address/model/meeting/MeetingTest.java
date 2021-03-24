package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MeetingTest {

    private static final String VALID_MEETING = "MRT;16/08/2021;15:30";
    private static final String INVALID_MEETING = "MRT 16/08/2021 15:30";
    private static final String VALID_PLACE = "LRT";
    private static final String INVALID_PLACE = "      ABC      ";
    private static final String VALID_DATE = "23/12/2021";
    private static final String INVALID_DATE = "34/23/2021";
    private static final String VALID_TIME = "18:29";
    private static final String INVALID_TIME = "24:70";

    @Test
    public void constructor_newMeeting_equal() {
        assertEquals(new Meeting(VALID_PLACE, VALID_DATE, VALID_TIME),
                Meeting.newMeeting(VALID_PLACE + ";" + VALID_DATE + ";" + VALID_TIME));
    }

    @Test
    public void constructor_newMeeting_notEqual() {
        assertNotEquals(new Meeting(VALID_PLACE, VALID_DATE, VALID_TIME),
                Meeting.newMeeting(VALID_MEETING));
    }

    @Test
    public void isValidMeeting() {
        // valid meeting
        assertTrue(Meeting.isValidMeeting(VALID_MEETING));

        // invalid meeting
        assertFalse(Meeting.isValidMeeting(INVALID_MEETING));

        // valid place date time
        assertTrue(Meeting.isValidMeeting(VALID_PLACE, VALID_DATE, VALID_TIME));

        // invalid place
        assertFalse(Meeting.isValidMeeting(INVALID_PLACE, VALID_DATE, VALID_TIME));

        // invalid date
        assertFalse(Meeting.isValidMeeting(VALID_PLACE, INVALID_DATE, VALID_TIME));

        // invalid time
        assertFalse(Meeting.isValidMeeting(VALID_PLACE, VALID_DATE, INVALID_TIME));
    }
}
