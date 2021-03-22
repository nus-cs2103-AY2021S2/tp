package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalItems.APPLE;
import static seedu.storemando.testutil.TypicalItems.BANANA;

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
        assertTrue(APPLE.isSameItem(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameItem(null));

        // same name and location, all other attributes different -> returns true
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA)
            .withExpiryDate(VALID_EXPIRYDATE_BANANA).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(APPLE.isSameItem(editedApple));

        // same name, all other attributes different -> returns false
        editedApple = new ItemBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA).withExpiryDate(VALID_EXPIRYDATE_BANANA)
            .withLocation(VALID_LOCATION_BANANA).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(APPLE.isSameItem(editedApple));

        // different name, all other attributes same -> returns false
        editedApple = new ItemBuilder(APPLE).withName(VALID_NAME_BANANA).build();
        assertFalse(APPLE.isSameItem(editedApple));

        // different location, all other attributes same -> returns false
        editedApple = new ItemBuilder(APPLE).withLocation(VALID_LOCATION_BANANA).build();
        assertFalse(APPLE.isSameItem(editedApple));

        // name differs in case, all other attributes same -> returns false
        Item editedBanana = new ItemBuilder(BANANA).withName(VALID_NAME_BANANA.toLowerCase()).build();
        assertFalse(BANANA.isSameItem(editedBanana));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BANANA + " ";
        editedBanana = new ItemBuilder(BANANA).withName(nameWithTrailingSpaces).build();
        assertFalse(BANANA.isSameItem(editedBanana));
    }

    @Test
    public void isSimilarItem() {
        // same object -> returns true
        assertTrue(APPLE.isSimilarItem(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSimilarItem(null));

        // same name and location, all other attributes different -> returns true
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA)
            .withExpiryDate(VALID_EXPIRYDATE_BANANA).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(APPLE.isSimilarItem(editedApple));

        // name differs in case, all other attributes same -> returns true
        Item editedBanana = new ItemBuilder(BANANA).withName(VALID_NAME_BANANA.toLowerCase()).build();
        assertTrue(BANANA.isSimilarItem(editedBanana));

        // name differs in case, all other attributes same -> returns true
        editedBanana = new ItemBuilder(BANANA).withName(VALID_NAME_BANANA.toUpperCase()).build();
        assertTrue(BANANA.isSimilarItem(editedBanana));

        // same name, all other attributes different -> returns false
        editedApple = new ItemBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA).withExpiryDate(VALID_EXPIRYDATE_BANANA)
            .withLocation(VALID_LOCATION_BANANA).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(APPLE.isSimilarItem(editedApple));

        // different name, all other attributes same -> returns false
        editedApple = new ItemBuilder(APPLE).withName(VALID_NAME_BANANA).build();
        assertFalse(APPLE.isSimilarItem(editedApple));

        // different location, all other attributes same -> returns false
        editedApple = new ItemBuilder(APPLE).withLocation(VALID_LOCATION_BANANA).build();
        assertFalse(APPLE.isSimilarItem(editedApple));


        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BANANA + " ";
        editedBanana = new ItemBuilder(BANANA).withName(nameWithTrailingSpaces).build();
        assertFalse(BANANA.isSimilarItem(editedBanana));
    }

    @Test
    public void isExpiredItem() {
        //Item with expired expiry date
        Item editedApple = new ItemBuilder(APPLE).withExpiryDate("2020-10-10").build();
        assertTrue(editedApple.isExpired());

        //Item with non expired expiry date
        Item editedBanana = new ItemBuilder(BANANA).withExpiryDate("2021-10-10").build();
        assertFalse(editedBanana.isExpired());

        //Item with no expiry date
        Item item = new ItemBuilder().withExpiryDate("No Expiry Date").build();
        assertFalse(item.isExpired());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Item appleCopy = new ItemBuilder(APPLE).build();
        assertTrue(APPLE.equals(appleCopy));

        // same object -> returns true
        assertTrue(APPLE.equals(APPLE));

        // null -> returns false
        assertFalse(APPLE.equals(null));

        // different type -> returns false
        assertFalse(APPLE.equals(5));

        // different item -> returns false
        assertFalse(APPLE.equals(BANANA));

        // different name -> returns false
        Item editedApple = new ItemBuilder(APPLE).withName(VALID_NAME_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // different quantity -> returns false
        editedApple = new ItemBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // different expirydate -> returns false
        editedApple = new ItemBuilder(APPLE).withExpiryDate(VALID_EXPIRYDATE_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // different location -> returns false
        editedApple = new ItemBuilder(APPLE).withLocation(VALID_LOCATION_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // different tags -> returns false
        editedApple = new ItemBuilder(APPLE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(APPLE.equals(editedApple));
    }
}
