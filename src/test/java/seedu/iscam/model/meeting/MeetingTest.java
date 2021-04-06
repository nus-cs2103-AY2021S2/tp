package seedu.iscam.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.testutil.TypicalMeetings.ALICE_1;
import static seedu.iscam.testutil.TypicalMeetings.CARL_1;

import org.junit.jupiter.api.Test;

import seedu.iscam.testutil.MeetingBuilder;

public class MeetingTest {
    @Test
    public void isInConflict() {
        // Same meeting -> returns true
        assertTrue(CARL_1.isInConflict(CARL_1));

        // Null -> returns false
        assertFalse(CARL_1.isInConflict(null));

        // Same date time, all other attributes different -> returns true
        Meeting aliceSameDate = new MeetingBuilder(ALICE_1).withDateTime(CARL_1.getDateTime().toString()).build();
        assertTrue(CARL_1.isInConflict(aliceSameDate));

        // Different date time, all other attributes same -> returns false
        Meeting carlDifferentDate = new MeetingBuilder(CARL_1).withDateTime(ALICE_1.getDateTime().toString()).build();
        assertFalse(CARL_1.isInConflict(carlDifferentDate));

        // Different date time, all other attributes different -> returns false
        assertFalse(CARL_1.isInConflict(ALICE_1));
    }

    @Test
    public void equals() {
        // Null -> returns false
        assertNotEquals(CARL_1, null);

        // Different meeting, all attributes same -> returns true
        Meeting carlCopy = new MeetingBuilder(CARL_1).build();
        assertEquals(carlCopy, CARL_1);

        // Different meeting, name same, all other attributes different -> returns false
        Meeting carlEdited = new MeetingBuilder(ALICE_1).withName(CARL_1.getClientName().toString()).build();
        assertNotEquals(carlEdited, CARL_1);

        // Different meeting, date and time same, all other attributes different -> returns false
        carlEdited = new MeetingBuilder(ALICE_1).withDateTime(CARL_1.getDateTime().toString()).build();
        assertNotEquals(carlEdited, CARL_1);

        // Different meeting, location same, all other attributes different -> returns false
        carlEdited = new MeetingBuilder(ALICE_1).withLocation(CARL_1.getLocation().toString()).build();
        assertNotEquals(carlEdited, CARL_1);

        // Different meeting, description same, all other attributes different -> returns false
        carlEdited = new MeetingBuilder(ALICE_1).withDescription(CARL_1.getDescription().toString()).build();
        assertNotEquals(carlEdited, CARL_1);

        // Different meeting, status same, all other attributes different -> returns false
        carlEdited = new MeetingBuilder(ALICE_1).withStatus(CARL_1.getStatus().toString()).build();
        assertNotEquals(carlEdited, CARL_1);

        // Different meeting, all attributes different -> returns false
        assertNotEquals(ALICE_1, CARL_1);
    }
}
