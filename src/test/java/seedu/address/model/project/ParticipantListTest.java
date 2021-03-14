package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

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
    public void getCopyOf_validParticipantList_copyOfOriginal() {
        ParticipantList participantList = new ParticipantList();
        ParticipantList participantListCopy = participantList.getCopy();
        assertEquals(participantList, participantListCopy);
        assertFalse(participantList == participantListCopy);
    }

}
