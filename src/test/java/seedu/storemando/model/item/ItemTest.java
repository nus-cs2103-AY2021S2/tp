package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalItems.BANANA;
import static seedu.storemando.testutil.TypicalItems.BREAD;
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

        // same name and location, all other attributes different -> returns true
        Item editedMilk = new ItemBuilder(MILK).withQuantity(VALID_QUANTITY_BANANA)
            .withExpiryDate(VALID_EXPIRYDATE_BANANA).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(MILK.isSameItem(editedMilk));

        // same name, all other attributes different -> returns false
        editedMilk = new ItemBuilder(MILK).withQuantity(VALID_QUANTITY_BANANA).withExpiryDate(VALID_EXPIRYDATE_BANANA)
            .withLocation(VALID_LOCATION_BANANA).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(MILK.isSameItem(editedMilk));

        // different name, all other attributes same -> returns false
        editedMilk = new ItemBuilder(MILK).withName(VALID_NAME_BANANA).build();
        assertFalse(MILK.isSameItem(editedMilk));

        // different location, all other attributes same -> returns false
        editedMilk = new ItemBuilder(MILK).withLocation(VALID_LOCATION_BANANA).build();
        assertFalse(MILK.isSameItem(editedMilk));

        // name differs in case, all other attributes same -> returns false
        Item editedBanana = new ItemBuilder(BANANA).withName(VALID_NAME_BANANA.toLowerCase()).build();
        assertFalse(BANANA.isSameItem(editedBanana));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BANANA + " ";
        editedBanana = new ItemBuilder(BANANA).withName(nameWithTrailingSpaces).build();
        assertFalse(BANANA.isSameItem(editedBanana));
    }

    @Test
    public void isExpiredItem() {
        //Item with expired expiry date
        Item editedAlice = new ItemBuilder(MILK).withExpiryDate("2020-10-10").build();
        assertTrue(editedAlice.isExpired());

        //Item with non expired expiry date
        Item editedBob = new ItemBuilder(BREAD).withExpiryDate("2021-10-10").build();
        assertFalse(editedBob.isExpired());

        //Item with no expiry date
        Item item = new ItemBuilder().withExpiryDate("No Expiry Date").build();
        assertFalse(item.isExpired());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Item milkCopy = new ItemBuilder(MILK).build();
        assertTrue(MILK.equals(milkCopy));

        // same object -> returns true
        assertTrue(MILK.equals(MILK));

        // null -> returns false
        assertFalse(MILK.equals(null));

        // different type -> returns false
        assertFalse(MILK.equals(5));

        // different item -> returns false
        assertFalse(MILK.equals(BANANA));

        // different name -> returns false
        Item editedMilk = new ItemBuilder(MILK).withName(VALID_NAME_BANANA).build();
        assertFalse(MILK.equals(editedMilk));

        // different quantity -> returns false
        editedMilk = new ItemBuilder(MILK).withQuantity(VALID_QUANTITY_BANANA).build();
        assertFalse(MILK.equals(editedMilk));

        // different expirydate -> returns false
        editedMilk = new ItemBuilder(MILK).withExpiryDate(VALID_EXPIRYDATE_BANANA).build();
        assertFalse(MILK.equals(editedMilk));

        // different location -> returns false
        editedMilk = new ItemBuilder(MILK).withLocation(VALID_LOCATION_BANANA).build();
        assertFalse(MILK.equals(editedMilk));

        // different tags -> returns false
        editedMilk = new ItemBuilder(MILK).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(MILK.equals(editedMilk));
    }
}
