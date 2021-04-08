package seedu.iscam.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.testutil.TypicalMeetings.ALICE_1;
import static seedu.iscam.testutil.TypicalMeetings.CARL_1;
import static seedu.iscam.testutil.TypicalMeetings.DANIEL_CONFLICTED_WITH_FIONA;
import static seedu.iscam.testutil.TypicalMeetings.FIONA_1;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.meeting.exceptions.MeetingConflictException;
import seedu.iscam.model.util.meetingbook.MeetingBook;
import seedu.iscam.model.util.meetingbook.ReadOnlyMeetingBook;
import seedu.iscam.testutil.MeetingBuilder;

public class MeetingBookTest {

    private final MeetingBook meetingBook = new MeetingBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), meetingBook.getMeetingList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingBook.resetData((null)));
    }

    @Test
    public void resetData_validBook_replacesData() {
        MeetingBook newData = getTypicalMeetingBook();
        meetingBook.resetData(newData);
        assertEquals(newData, meetingBook);
    }

    @Test
    public void resetData_bookWithDuplicates_throwsMeetingConflictException() {
        Meeting editedFiona = new MeetingBuilder(FIONA_1)
                .withLocation(ALICE_1.getLocation().toString())
                .withDescription(CARL_1.getDescription().toString())
                .withStatus(ALICE_1.getStatus().toString())
                .build();

        List<Meeting> newMeetings = Arrays.asList(FIONA_1, editedFiona);
        MeetingBookStub newData = new MeetingBookStub(newMeetings);

        assertThrows(MeetingConflictException.class, () -> meetingBook.resetData(newData));
    }

    @Test
    public void hasMeeting_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingBook.hasMeeting(null));
    }

    @Test
    public void hasMeeting_meetingNotInMeetingBook_returnsFalse() {
        assertFalse(meetingBook.hasMeeting(ALICE_1));
    }

    @Test
    public void hasMeeting_meetingInMeetingBook_returnsTrue() {
        meetingBook.addMeeting(FIONA_1);
        assertTrue(meetingBook.hasMeeting(FIONA_1));
    }

    @Test
    public void hasMeeting_meetingWithSameIdentityInBook_returnsTrue() {
        meetingBook.addMeeting(FIONA_1);
        Meeting editedFiona = new MeetingBuilder(FIONA_1)
                .withLocation(ALICE_1.getLocation().toString())
                .withDescription(CARL_1.getDescription().toString())
                .withStatus(ALICE_1.getStatus().toString())
                .build();
        assertTrue(meetingBook.hasMeeting(editedFiona));
    }

    @Test
    public void hasConflictingMeetingWith_gotConflict_returnsTrue() {
        meetingBook.addMeeting(FIONA_1);
        meetingBook.addMeeting(ALICE_1);

        // Without exclusion
        assertTrue(meetingBook.hasConflictingMeetingWith(DANIEL_CONFLICTED_WITH_FIONA));

        // With exclusions
        assertTrue(meetingBook.hasConflictingMeetingWith(DANIEL_CONFLICTED_WITH_FIONA, ALICE_1, CARL_1));
    }

    @Test
    public void hasConflictingMeetingWith_gotNoConflict_returnsFalse() {
        meetingBook.addMeeting(FIONA_1);
        meetingBook.addMeeting(ALICE_1);

        // Without exclusion
        assertFalse(meetingBook.hasConflictingMeetingWith(CARL_1));

        // Through exclusion
        assertFalse(meetingBook.hasConflictingMeetingWith(DANIEL_CONFLICTED_WITH_FIONA, FIONA_1));
    }

    private static class MeetingBookStub implements ReadOnlyMeetingBook {
        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

        MeetingBookStub(Collection<Meeting> meetings) {
            this.meetings.setAll(meetings);
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            return meetings;
        }
    }
}
