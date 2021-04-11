package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimeslotClashTest {
    @Test
    public void isClashing() {
        assertFalse(new Timeslot("12:00 to 13:30")
                .isClashingWith(new Timeslot("14:00 to 15:00"))); // first completely before second
        assertFalse(new Timeslot("12:00 to 13:30")
                .isClashingWith(new Timeslot("13:30 to 14:00"))); // first ending and second beginning overlap
        assertFalse(new Timeslot("12:00 to 13:00")
                .isClashingWith(new Timeslot("10:00 to 11:00"))); // first completely after second
        assertFalse(new Timeslot("12:00 to 13:00")
                .isClashingWith(new Timeslot("11:00 to 12:00"))); // first beginning and second ending overlap

        // same
        assertTrue(new Timeslot("12:00 to 13:00")
                .isClashingWith(new Timeslot("12:00 to 13:00"))); // first completely before second

        // subset
        assertTrue(new Timeslot("12:00 to 13:00")
                .isClashingWith(new Timeslot("11:59 to 13:01"))); // first subset of second
        assertTrue(new Timeslot("10:00 to 13:00")
                .isClashingWith(new Timeslot("10:00 to 12:00"))); // second subset of first

        // overlap
        assertTrue(new Timeslot("11:00 to 12:00")
                .isClashingWith(new Timeslot("10:00 to 11:30")));
    }
}
