package seedu.budgetbaby.model.record;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTest {
    private Category category = new Category("Food");

    @Test
    public void getCategory() {
        assertEquals("Food", category.getCategory());
    }

    @Test
    public void isValidCategory() {
        // invalid category
        assertFalse(Category.isValidTagName("")); // empty string
        assertFalse(Category.isValidTagName("AAAAAAAAAAAAAAAAAAAA")); // more than 15 characters

        // valid category
        assertTrue(Category.isValidTagName(" ")); // spaces only
        assertTrue(Category.isValidTagName("food")); // standard
        assertTrue(Category.isValidTagName("movie fun")); // multiple words
        assertTrue(Category.isValidTagName("!#$%&'*+/=?`")); // special characters
        assertTrue(Category.isValidTagName("123456")); // numeric only
        assertTrue(Category.isValidTagName("movie!` 123")); // mixture of alphanumeric and special characters
    }

}
