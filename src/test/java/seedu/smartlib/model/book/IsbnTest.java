package seedu.smartlib.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IsbnTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Isbn(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidIsbn = "";
        assertThrows(IllegalArgumentException.class, () -> new Isbn(invalidIsbn));
    }

    @Test
    public void isValidIsbn() {
        // null Isbn
        assertThrows(NullPointerException.class, () -> Isbn.isValidIsbn(null));

        // invalid Isbn numbers
        assertFalse(Isbn.isValidIsbn("")); // empty string
        assertFalse(Isbn.isValidIsbn(" ")); // spaces only
        assertFalse(Isbn.isValidIsbn("91")); // less than 13 numbers
        assertFalse(Isbn.isValidIsbn("aaaaaaaaaaaaa")); // non-numeric with alphabets only
        assertFalse(Isbn.isValidIsbn("/////////////")); // non-numeric with special characters only
        assertFalse(Isbn.isValidIsbn("isbn123456789")); // alphabets within digits
        assertFalse(Isbn.isValidIsbn("1234 56789012")); // spaces within digits
        assertFalse(Isbn.isValidIsbn("1234-56789012")); // characters within digits
        assertFalse(Isbn.isValidIsbn("1234567890123456")); // longer than 13 digits
        assertFalse(Isbn.isValidIsbn("1234567890123 ")); // exactly 13 digits with trailing spaces
        assertFalse(Isbn.isValidIsbn(" 1234567890123")); // exactly 13 digits starting with spaces

        // valid Isbn numbers
        assertTrue(Isbn.isValidIsbn("1234567890123")); // exactly 13 digits
        assertTrue(Isbn.isValidIsbn("9312153445782"));
        assertTrue(Isbn.isValidIsbn("1242938420331"));
    }
}
