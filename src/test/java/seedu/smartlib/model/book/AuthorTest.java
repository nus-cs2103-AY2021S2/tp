package seedu.smartlib.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.name.Name;

public class AuthorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Author(new Name(null)));
    }

    @Test
    public void constructor_invalidAuthor_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Author(new Name("")));
    }

    @Test
    public void isValidAuthor() {
        // null author
        assertThrows(NullPointerException.class, () -> Author.isValidAuthor(null));

        // invalid author
        assertFalse(Author.isValidAuthor("")); // empty string
        assertFalse(Author.isValidAuthor(" ")); // spaces only
        assertFalse(Author.isValidAuthor("Лев Николаевич Толстой")); // non-English author name
        assertFalse(Author.isValidAuthor("Stephen-King")); // author name with special characters
        assertFalse(Author.isValidAuthor(".")); // author name with characters only
        assertFalse(Author.isValidAuthor(" Brandon Sanderson")); // author name starting with spaces

        // valid author
        assertTrue(Author.isValidAuthor("J K Rowling")); // author name with spaces
        assertTrue(Author.isValidAuthor("Stephen King ")); // author name with trailing spaces
        assertTrue(Author.isValidAuthor("Aleksey Nikolayevich Tolstoy")); // very long author name
        assertTrue(Author.isValidAuthor("A")); // one character author name

    }
}
