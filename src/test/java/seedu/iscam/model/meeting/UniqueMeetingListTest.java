package seedu.iscam.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.testutil.TypicalMeetings.ALICE_1;
import static seedu.iscam.testutil.TypicalMeetings.CARL_1;
import static seedu.iscam.testutil.TypicalMeetings.FIONA_1;
import static seedu.iscam.testutil.TypicalMeetings.FIONA_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.iscam.model.meeting.exceptions.MeetingConflictException;
import seedu.iscam.model.meeting.exceptions.MeetingNotFoundException;
import seedu.iscam.testutil.MeetingBuilder;

public class UniqueMeetingListTest {

    private final UniqueMeetingList list = new UniqueMeetingList();

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> list.contains(null));
    }

    @Test
    public void contains_notInList_returnsFalse() {
        assertFalse(list.contains(FIONA_1));
    }

    @Test
    public void contains_inList_returnsTrue() {
        list.add(FIONA_1);
        assertTrue(list.contains(FIONA_1));
    }

    @Test
    public void contains_meetingWithSameIdentity_returnsTrue() {
        list.add(FIONA_1);
        Meeting editedFiona = new MeetingBuilder(FIONA_1)
                .withLocation(ALICE_1.getLocation().toString())
                .withDescription(CARL_1.getDescription().toString())
                .withStatus(FIONA_2.getStatus().toString())
                .build();
        assertTrue(list.contains(editedFiona));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> list.add(null));
    }

    @Test
    public void add_conflictingMeeting_throwsMeetingConflictException() {
        list.add(FIONA_1);
        assertThrows(MeetingConflictException.class, () -> list.add(FIONA_1));
    }

    @Test
    public void setMeeting_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> list.setMeeting(null, FIONA_1));
    }

    @Test
    public void setMeeting_nullEdited_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> list.setMeeting(FIONA_1, null));
    }

    @Test
    public void setMeeting_targetNotInList_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> list.setMeeting(FIONA_1, FIONA_1));
    }

    @Test
    public void setMeeting_editedNoChange_success() {
        list.add(FIONA_1);
        list.setMeeting(FIONA_1, FIONA_1);

        UniqueMeetingList expectedList = new UniqueMeetingList();
        expectedList.add(FIONA_1);

        assertEquals(expectedList, list);
    }

    @Test
    public void setMeeting_editedSameIdentity_throwsMeetingConflictException() {
        list.add(FIONA_1);
        Meeting editedFiona = new MeetingBuilder(FIONA_1)
                .withLocation(ALICE_1.getLocation().toString())
                .withDescription(CARL_1.getDescription().toString())
                .withStatus(FIONA_2.getStatus().toString())
                .build();
        list.setMeeting(FIONA_1, editedFiona);

        UniqueMeetingList expectedList = new UniqueMeetingList();
        expectedList.add(editedFiona);

        assertEquals(expectedList, list);
    }

    @Test
    public void setMeeting_editedDifferentIdentity_success() {
        list.add(FIONA_1);
        Meeting editedAlice = new MeetingBuilder(ALICE_1)
                .withLocation(FIONA_1.getLocation().toString())
                .withDescription(FIONA_1.getDescription().toString())
                .withStatus(FIONA_1.getStatus().toString())
                .build();
        list.setMeeting(FIONA_1, editedAlice);

        UniqueMeetingList expectedList = new UniqueMeetingList();
        expectedList.add(editedAlice);

        assertEquals(expectedList, list);
    }

    @Test
    public void setMeeting_editedCompletelyDifferent_success() {
        list.add(FIONA_1);
        list.setMeeting(FIONA_1, ALICE_1);

        UniqueMeetingList expectedList = new UniqueMeetingList();
        expectedList.add(ALICE_1);

        assertEquals(expectedList, list);
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> list.remove(null));
    }

    @Test
    public void remove_toRemoveNotInList_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> list.remove(CARL_1));
    }

    @Test
    public void remove_existingMeeting_success() {
        list.add(FIONA_1);
        list.remove(FIONA_1);

        UniqueMeetingList expectedList = new UniqueMeetingList();

        assertEquals(expectedList, list);
    }

    @Test
    public void setMeetings_nullUniqueList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> list.setMeetings((UniqueMeetingList) null));
    }

    @Test
    public void setMeetings_distinctUniqueLists_replacesCurrentListWithProvidedList() {
        list.add(FIONA_1);

        UniqueMeetingList expectedList = new UniqueMeetingList();
        expectedList.add(ALICE_1);

        list.setMeetings(expectedList);
        assertEquals(expectedList, list);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> list.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_distinctLists_replacesCurrentListWithProvidedList() {
        list.add(FIONA_1);
        List<Meeting> meetingList = Collections.singletonList(ALICE_1);
        list.setMeetings(meetingList);

        UniqueMeetingList expectedList = new UniqueMeetingList();
        expectedList.add(ALICE_1);

        assertEquals(expectedList, list);
    }

    @Test
    public void setMeetings_listWithConflicts_throwsMeetingConflictException() {
        List<Meeting> duplicatesList = Arrays.asList(FIONA_1, FIONA_1);
        assertThrows(MeetingConflictException.class, () -> list.setMeetings(duplicatesList));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> list.asUnmodifiableObservableList().remove(0));
    }
}
