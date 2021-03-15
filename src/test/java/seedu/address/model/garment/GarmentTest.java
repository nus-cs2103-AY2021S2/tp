package seedu.address.model.garment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COLOUR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGarments.ALICE;
import static seedu.address.testutil.TypicalGarments.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GarmentBuilder;

public class GarmentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Garment garment = new GarmentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> garment.getDescriptions().remove(0));
    }

    @Test
    public void isSameGarment() {
        // same object -> returns true
        assertTrue(ALICE.isSameGarment(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameGarment(null));

        // same name, all other attributes different -> returns true
        Garment editedAlice = new GarmentBuilder(ALICE).withSize(VALID_SIZE_BOB).withColour(VALID_COLOUR_BOB)
                .withDressCode(VALID_DRESSCODE_BOB).withDescriptions(VALID_DESCRIPTION_HUSBAND).build();
        assertTrue(ALICE.isSameGarment(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new GarmentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameGarment(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Garment editedBob = new GarmentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameGarment(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new GarmentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameGarment(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Garment aliceCopy = new GarmentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different garment -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Garment editedAlice = new GarmentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different size -> returns false
        editedAlice = new GarmentBuilder(ALICE).withSize(VALID_SIZE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different colour -> returns false
        editedAlice = new GarmentBuilder(ALICE).withColour(VALID_COLOUR_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new GarmentBuilder(ALICE).withDressCode(VALID_DRESSCODE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different descriptions -> returns false
        editedAlice = new GarmentBuilder(ALICE).withDescriptions(VALID_DESCRIPTION_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
