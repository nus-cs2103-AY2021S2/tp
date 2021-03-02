package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.ALICE;
import static seedu.address.testutil.TypicalItems.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class ItemTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Item item = new ItemBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> item.getTags().remove(0));
    }

    @Test
    public void isSameItem() {
        // same object -> returns true
        assertTrue(ALICE.isSameItem(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameItem(null));

        // same name, all other attributes different -> returns true
        Item editedAlice = new ItemBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withLocation(VALID_LOCATION_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameItem(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ItemBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameItem(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Item editedBob = new ItemBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameItem(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ItemBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameItem(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Item aliceCopy = new ItemBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different item -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Item editedAlice = new ItemBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ItemBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ItemBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ItemBuilder(ALICE).withLocation(VALID_LOCATION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ItemBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
