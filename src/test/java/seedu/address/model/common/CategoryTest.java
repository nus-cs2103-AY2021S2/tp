package seedu.address.model.common;

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
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidCategoryName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidCategoryName));
    }

    @Test
    public void isValidCategoryName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Category.isValidCategoryName(null));
    }

    @Test
    public void isValidCategory() {
        // invalid category
        assertFalse(Category.isValidCategoryName("")); // empty string
        assertFalse(Category.isValidCategoryName(" ")); // spaces only
        assertFalse(Category.isValidCategoryName("^")); // only non-alphanumeric characters
        assertFalse(Category.isValidCategoryName("do * at #")); // contains non-alphanumeric characters
        assertFalse(Category.isValidCategoryName("CS2103SoftwareProgrammingLecture2")); // overly long names

        // valid category
        assertTrue(Category.isValidCategoryName("lecture")); // alphabets only
        assertTrue(Category.isValidCategoryName("1337")); // numbers only
        assertTrue(Category.isValidCategoryName("cs2103lecture")); // alphanumeric characters
        assertTrue(Category.isValidCategoryName("OfficialMeeting")); // with capital letters
        assertTrue(Category.isValidCategoryName("CS2103Software1")); // long names - hits 15 char limit
    }
}
