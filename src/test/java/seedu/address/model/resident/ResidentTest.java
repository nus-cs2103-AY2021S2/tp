package seedu.address.model.resident;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.resident.TypicalResidents.ALICE;
import static seedu.address.testutil.resident.TypicalResidents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.resident.ResidentBuilder;

public class ResidentTest {

    /*
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Resident resident = new ResidentRoomBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> resident.getPhone().remove());
    }
    */

    @Test
    public void isSameResident() {
        // same object -> returns true
        assertTrue(ALICE.isSameResident(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameResident(null));

        // same name, all other attributes different -> returns true
        Resident editedAlice = new ResidentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        assertTrue(ALICE.isSameResident(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ResidentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameResident(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Resident editedBob = new ResidentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameResident(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ResidentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameResident(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Resident aliceCopy = new ResidentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different resident -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Resident editedAlice = new ResidentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ResidentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ResidentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
