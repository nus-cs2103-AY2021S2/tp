package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PERSON_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_BOB;

import org.junit.jupiter.api.Test;

public class PersonIdTest {

    @Test
    public void isValidPersonId_invalidPersonId_failure() {
        assertFalse(PersonId.isValidPersonId(INVALID_PERSON_ID_DESC));
    }

    @Test
    public void isValidPersonId_validPersonId_success() {
        assertTrue(PersonId.isValidPersonId(VALID_PERSON_ID_AMY));
    }

    @Test
    public void equals() {
        PersonId firstPersonId = new PersonId(VALID_PERSON_ID_AMY);
        PersonId secondPersonId = new PersonId(VALID_PERSON_ID_BOB);

        // same values -> returns true
        PersonId firstPersonIdCopy = new PersonId(VALID_PERSON_ID_AMY);
        assertTrue(firstPersonId.equals(firstPersonIdCopy));

        // same object -> returns true
        assertTrue(firstPersonId.equals(firstPersonId));

        // null -> returns false
        assertFalse(firstPersonId.equals(null));

        // different type -> returns false
        assertFalse(firstPersonId.equals(5));

        // different person ID -> returns false
        assertFalse(firstPersonId.equals(secondPersonId));
    }
}
