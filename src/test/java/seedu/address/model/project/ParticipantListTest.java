package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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

}
