package seedu.flashback.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Category(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Category.isValidCategory(null));

        // invalid category
        assertFalse(Category.isValidCategory("")); // empty string
        assertFalse(Category.isValidCategory(" ")); // spaces only
        assertFalse(Category.isValidCategory("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); // 31 characters

        // valid category
        assertTrue(Category.isValidCategory("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); // 30 characters
        assertTrue(Category.isValidCategory("aaaaaaaaaaaaaaaaaaaaa aaaaaaaa")); // 30 characters with space
    }
}
