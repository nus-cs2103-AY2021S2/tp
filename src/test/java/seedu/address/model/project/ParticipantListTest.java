package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

public class ParticipantListTest {

    @Test
    public void constructor_empty_createEmptyParticipantList() {
        ParticipantList emptyParticipantList = new ParticipantList();
        assertTrue(emptyParticipantList.getParticipants().isEmpty());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ParticipantList(null));
    }

    @Test
    public void constructor_singleParticipant_success() {
        ArrayList<Person> participants = new ArrayList<>();
        participants.add(ALICE);
        assertDoesNotThrow(() -> new ParticipantList(participants));
    }

    @Test
    public void getPaticipants_validParticipantsList_equalsOriginalList() {
        ArrayList<Person> participants = new ArrayList<>();
        participants.add(ALICE);
        ParticipantList participantList = new ParticipantList(participants);
        assertEquals(participants, participantList.getParticipants());
    }

    @Test
    public void size_validParticipantsList_correct() {
        ArrayList<Person> participants = new ArrayList<>();
        participants.add(ALICE);
        ParticipantList participantList = new ParticipantList(participants);
        assertEquals(participants.size(), participantList.size());
        participants.add(BOB);
        participantList = new ParticipantList(participants);
        assertEquals(participants.size(), participantList.size());
    }

    @Test
    public void get_validParticipantsList_correct() {
        ArrayList<Person> participants = new ArrayList<>();
        participants.add(ALICE);
        ParticipantList participantList = new ParticipantList(participants);
        assertEquals(participants.get(0), participantList.get(0));
        participants.add(BOB);
        participantList = new ParticipantList(participants);
        assertEquals(participants.get(0), participantList.get(0));
        assertEquals(participants.get(1), participantList.get(1));
    }

    @Test
    public void delete_validParticipantsList_correct() {
        ArrayList<Person> participants = new ArrayList<>();
        participants.add(ALICE);
        participants.add(BOB);
        ParticipantList participantList = new ParticipantList(participants);
        assertEquals(2, participantList.size());
        participantList.delete(0);
        assertEquals(1, participantList.size());
        assertEquals(participantList.get(0), BOB);
        participantList.delete(0);
        assertEquals(0, participantList.size());
    }

    @Test
    public void getCopyOf_validParticipantList_copyOfOriginal() {
        ParticipantList participantList = new ParticipantList();
        ParticipantList participantListCopy = participantList.getCopy();
        assertEquals(participantList, participantListCopy);
        assertFalse(participantList == participantListCopy);
    }

}
