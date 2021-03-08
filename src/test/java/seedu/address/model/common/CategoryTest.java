package seedu.address.model.common;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CategoryTest {

    /* TO REACTIVATE when requireNotNull is added
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }
     */

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidCategoryName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidCategoryName));
    }

    /* TO REACTIVATE when validity check is merged from PR.
    @Test
    public void isValidCategoryName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Category.isValidCategoryName(null));
    }
     */
}
