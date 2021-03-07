package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_ESSENTIAL;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalItems.ALICE;
import static seedu.storemando.testutil.TypicalItems.BOB;

import org.junit.jupiter.api.Test;

import seedu.storemando.testutil.ItemBuilder;

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

        Item editedAlice = new ItemBuilder(ALICE).withQuantity(VALID_QUANTITY_BANANA).withExpiryDate(VALID_EXPIRYDATE_BANANA)
            .withLocation(VALID_LOCATION_BANANA).withTags(VALID_TAG_ESSENTIAL).build();
        assertTrue(ALICE.isSameItem(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ItemBuilder(ALICE).withName(VALID_NAME_BANANA).build();
        assertFalse(ALICE.isSameItem(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Item editedBob = new ItemBuilder(BOB).withName(VALID_NAME_BANANA.toLowerCase()).build();
        assertFalse(BOB.isSameItem(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BANANA + " ";
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
        Item editedAlice = new ItemBuilder(ALICE).withName(VALID_NAME_BANANA).build();
        assertFalse(ALICE.equals(editedAlice));

        // different quantity -> returns false
        editedAlice = new ItemBuilder(ALICE).withQuantity(VALID_QUANTITY_BANANA).build();
        assertFalse(ALICE.equals(editedAlice));

        // different expirydate -> returns false
        editedAlice = new ItemBuilder(ALICE).withExpiryDate(VALID_EXPIRYDATE_BANANA).build();
        assertFalse(ALICE.equals(editedAlice));

        // different location -> returns false
        editedAlice = new ItemBuilder(ALICE).withLocation(VALID_LOCATION_BANANA).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ItemBuilder(ALICE).withTags(VALID_TAG_ESSENTIAL).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
