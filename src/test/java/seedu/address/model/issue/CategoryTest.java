package seedu.address.model.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }

    @Test
    public void constructor_invalidCategory_throwsIllegalArgumentException() {
        String invalidCategory = "-";
        assertThrows(IllegalArgumentException.class, () -> new Category(invalidCategory));
    }

    @Test
    public void isValidName() {
        // null category
        assertThrows(NullPointerException.class, () -> Category.isValidCategory(null));

        // invalid categories
        assertFalse(Category.isValidCategory("")); // empty category
        assertFalse(Category.isValidCategory(" abc")); // start with whitespace
        assertFalse(Category.isValidCategory("-")); // non alpha-numerical

        // valid categories
        assertTrue(Category.isValidCategory("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMOPQRSTUVWXYZ")); // alphabet
        assertTrue(Category.isValidCategory("1234567890")); // numbers
        // alphanumerical
        assertTrue(Category.isValidCategory("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMOPQRSTUVWXYZ1234567890"));
        assertTrue(Category.isValidCategory("Wood Furniture")); // alphabets with space
    }
}
