package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_CHEESE;
import static seedu.storemando.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ItemName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ItemName.isValidName(null));

        // invalid name
        assertFalse(ItemName.isValidName("")); // empty string
        assertFalse(ItemName.isValidName(" ")); // spaces only
        assertFalse(ItemName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(ItemName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ItemName.isValidName("peter jack")); // alphabets only
        assertTrue(ItemName.isValidName("12345")); // numbers only
        assertTrue(ItemName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(ItemName.isValidName("Capital Tan")); // with capital letters
        assertTrue(ItemName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void isSimilar() {
        assertTrue(new ItemName(VALID_NAME_CHEESE).isSimilar(new ItemName("cheese")));
        assertTrue(new ItemName(VALID_NAME_CHEESE).isSimilar(new ItemName("CHEESE")));
        assertTrue(new ItemName(VALID_NAME_CHEESE).isSimilar(new ItemName("cHeEsE")));

        assertFalse(new ItemName(VALID_NAME_CHEESE).isSimilar(new ItemName("chees")));
        assertFalse(new ItemName(VALID_NAME_CHEESE).isSimilar(new ItemName("cheesee")));
        assertFalse(new ItemName(VALID_NAME_CHEESE).isSimilar(new ItemName("CHEES")));
        assertFalse(new ItemName(VALID_NAME_CHEESE).isSimilar(new ItemName("CHEESEE")));
        assertFalse(new ItemName(VALID_NAME_CHEESE).isSimilar(new ItemName("chEES")));
        assertFalse(new ItemName(VALID_NAME_CHEESE).isSimilar(new ItemName("chEESeE")));
    }

    @Test
    public void compare_itemNames_success() {
        assertTrue(new ItemName(VALID_NAME_CHEESE).compare(new ItemName(VALID_NAME_BANANA)) > 0);
        assertTrue(new ItemName(VALID_NAME_BANANA).compare(new ItemName(VALID_NAME_CHEESE)) < 0);
        assertTrue(new ItemName(VALID_NAME_CHEESE).compare(new ItemName(VALID_NAME_CHEESE)) == 0);

    }


}
