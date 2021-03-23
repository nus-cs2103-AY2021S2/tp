package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_DESC_CLASH_PRANK;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_DESC_PRANK;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_DESC_STH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.MeetingClashException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

class UniqueMeetingListTest {
    private final UniqueMeetingList uniqueMeetingList = new UniqueMeetingList();
    private final Person editedAlice = new PersonBuilder(ALICE).withMeeting(MEETING_DESC_PRANK).build();

    @Test
    void clash_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.clash(null));
    }

    @Test
    public void clash_personNotInList_returnsFalse() {
        assertFalse(uniqueMeetingList.clash(ALICE));

        uniqueMeetingList.add(ALICE);
        assertFalse(uniqueMeetingList.clash(ALICE));

        uniqueMeetingList.add(editedAlice);
        assertFalse(uniqueMeetingList.clash(ALICE));
    }

    @Test
    public void clash_personInList_returnsTrue() {
        Person editedBob = new PersonBuilder(BOB).withMeeting(MEETING_DESC_CLASH_PRANK).build();
        uniqueMeetingList.add(editedAlice);
        assertTrue(uniqueMeetingList.clash(editedBob));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueMeetingList.add(editedAlice);
        Person editedAliceDuplicate = new PersonBuilder(ALICE).withMeeting(MEETING_DESC_STH).build();
        assertThrows(DuplicatePersonException.class, () -> uniqueMeetingList.add(editedAliceDuplicate));
    }

    @Test
    public void add_duplicatePerson_throwsMeetingClashException() {
        Person editedBob = new PersonBuilder(BOB).withMeeting(MEETING_DESC_CLASH_PRANK).build();
        uniqueMeetingList.add(editedAlice);
        assertThrows(MeetingClashException.class, () -> uniqueMeetingList.add(editedBob));
        assertThrows(MeetingClashException.class, () -> uniqueMeetingList.add(editedAlice));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        Person editedBob = new PersonBuilder(BOB).withMeeting(MEETING_DESC_CLASH_PRANK).build();
        uniqueMeetingList.add(ALICE);
        uniqueMeetingList.setPerson(ALICE, ALICE);
        uniqueMeetingList.add(editedBob);
        uniqueMeetingList.setPerson(editedBob, editedBob);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(ALICE);
        expectedUniqueMeetingList.add(editedBob);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentMeeting_success() {
        Person editedAliceDuplicate = new PersonBuilder(ALICE).withMeeting(MEETING_DESC_CLASH_PRANK).build();
        UniqueMeetingList expectedUniqueMeetingList;
        uniqueMeetingList.add(ALICE);

        uniqueMeetingList.setPerson(ALICE, editedAlice);
        expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedAlice);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);

        uniqueMeetingList.setPerson(editedAlice, editedAliceDuplicate);
        expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedAliceDuplicate);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);

        uniqueMeetingList.setPerson(editedAliceDuplicate, ALICE);
        expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        Person editedBob = new PersonBuilder(BOB).withMeeting(MEETING_DESC_PRANK).build();
        uniqueMeetingList.add(editedAlice);
        uniqueMeetingList.setPerson(editedAlice, editedBob);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedBob);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsMeetingClashException() {
        Person originalBob = new PersonBuilder(BOB).withMeeting(MEETING_DESC_STH).build();
        Person clashBob = new PersonBuilder(BOB).withMeeting(MEETING_DESC_CLASH_PRANK).build();
        uniqueMeetingList.add(editedAlice);
        uniqueMeetingList.add(originalBob);
        assertThrows(MeetingClashException.class, () -> uniqueMeetingList.setPerson(originalBob, clashBob));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_success() {
        uniqueMeetingList.remove(ALICE);
        uniqueMeetingList.remove(editedAlice);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueMeetingList.add(editedAlice);
        uniqueMeetingList.remove(editedAlice);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setPersons((UniqueMeetingList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        Person editedBob = new PersonBuilder(BOB).withMeeting(MEETING_DESC_CLASH_PRANK).build();
        uniqueMeetingList.add(ALICE);
        uniqueMeetingList.add(editedBob);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedAlice);
        uniqueMeetingList.setPersons(expectedUniqueMeetingList);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setPersons((List<Person>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        Person editedBob = new PersonBuilder(BOB).withMeeting(MEETING_DESC_CLASH_PRANK).build();
        uniqueMeetingList.add(editedAlice);
        List<Person> personList = Collections.singletonList(editedBob);
        uniqueMeetingList.setPersons(personList);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedBob);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueMeetingList.asUnmodifiableObservableList().remove(0));
    }
}
