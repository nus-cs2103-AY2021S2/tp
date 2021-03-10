package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    public void constructor_singleParticipant_valid() {
        ArrayList<Person> participants = new ArrayList<>();
        participants.add(ALICE);
        assertDoesNotThrow(() -> new ParticipantList(participants));
    }

}
