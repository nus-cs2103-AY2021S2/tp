package seedu.budgetbaby.abmodel.tag;

import static seedu.budgetbaby.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.budgetbaby.model.record.Category;

public class CategoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Category(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Category.isValidTagName(null));
    }

}
