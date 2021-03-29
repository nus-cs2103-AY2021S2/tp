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
        assertTrue(ItemName.isValidName("Coco Crunch")); // alphabets only
        assertTrue(ItemName.isValidName("12345")); // numbers only
        assertTrue(ItemName.isValidName("Fruit loops 2")); // alphanumeric characters
        assertTrue(ItemName.isValidName("Capital Frosties")); // with capital letters
        assertTrue(ItemName.isValidName("Pancake flour made by mom")); // long names
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

    @Test
    public void equals() {
        ItemName itemName = new ItemName(("A"));
        assertTrue(itemName.equals(itemName)); // same itemname object
        assertTrue(new ItemName("abc").equals(new ItemName("abc"))); // alphabets onl
        assertTrue(new ItemName("123").equals(new ItemName("123"))); // numbers only
        assertTrue(new ItemName("abc123").equals(new ItemName("abc123"))); // alphanumeric only

        assertFalse(new ItemName("abcc").equals(new ItemName("abc"))); // name is a prefix of other itemName
        assertFalse(new ItemName("10").equals(new ItemName("1"))); // number is a prefix of another
        assertFalse(new ItemName("ABC").equals(new ItemName("ABC1"))); // alphanumeric prefix
        assertFalse(new ItemName("aBc").equals(new ItemName("abC"))); // alphabets only in different cases

    }


}
