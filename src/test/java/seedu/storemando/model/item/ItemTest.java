package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_ESSENTIAL;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalItems.BANANA;
import static seedu.storemando.testutil.TypicalItems.MILK;

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
        assertTrue(MILK.isSameItem(MILK));

        // null -> returns false
        assertFalse(MILK.isSameItem(null));

        // same name, all other attributes different -> returns true

        Item editedAlice = new ItemBuilder(MILK).withQuantity(VALID_QUANTITY_BANANA)
            .withExpiryDate(VALID_EXPIRYDATE_BANANA).withLocation(VALID_LOCATION_BANANA)
            .withTags(VALID_TAG_ESSENTIAL).build();
        assertTrue(MILK.isSameItem(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ItemBuilder(MILK).withName(VALID_NAME_BANANA).build();
        assertFalse(MILK.isSameItem(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Item editedBob = new ItemBuilder(BANANA).withName(VALID_NAME_BANANA.toLowerCase()).build();
        assertFalse(BANANA.isSameItem(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BANANA + " ";
        editedBob = new ItemBuilder(BANANA).withName(nameWithTrailingSpaces).build();
        assertFalse(BANANA.isSameItem(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Item aliceCopy = new ItemBuilder(MILK).build();
        assertTrue(MILK.equals(aliceCopy));

        // same object -> returns true
        assertTrue(MILK.equals(MILK));

        // null -> returns false
        assertFalse(MILK.equals(null));

        // different type -> returns false
        assertFalse(MILK.equals(5));

        // different item -> returns false
        assertFalse(MILK.equals(BANANA));

        // different name -> returns false
        Item editedAlice = new ItemBuilder(MILK).withName(VALID_NAME_BANANA).build();
        assertFalse(MILK.equals(editedAlice));

        // different quantity -> returns false
        editedAlice = new ItemBuilder(MILK).withQuantity(VALID_QUANTITY_BANANA).build();
        assertFalse(MILK.equals(editedAlice));

        // different expirydate -> returns false
        editedAlice = new ItemBuilder(MILK).withExpiryDate(VALID_EXPIRYDATE_BANANA).build();
        assertFalse(MILK.equals(editedAlice));

        // different location -> returns false
        editedAlice = new ItemBuilder(MILK).withLocation(VALID_LOCATION_BANANA).build();
        assertFalse(MILK.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ItemBuilder(MILK).withTags(VALID_TAG_ESSENTIAL).build();
        assertFalse(MILK.equals(editedAlice));
    }
}
