package seedu.smartlib.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.name.Name;

public class GenreTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Genre(new Name(null)));
    }

    @Test
    public void constructor_invalidGenre_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Genre(new Name("")));
    }

    @Test
    public void isValidGenre() {
        // null genre
        assertThrows(NullPointerException.class, () -> Genre.isValidGenre(null));

        // invalid genre
        assertFalse(Genre.isValidGenre("")); // empty string
        assertFalse(Genre.isValidGenre(" ")); // spaces only
        assertFalse(Genre.isValidGenre("科幻")); // non-English genre name
        assertFalse(Genre.isValidGenre("Sci-Fi")); // genre name with special characters
        assertFalse(Genre.isValidGenre("-")); // genre name with character only
        assertFalse(Genre.isValidGenre(" Comic")); // genre name with leading space

        // valid genre
        assertTrue(Genre.isValidGenre("Young Adult Fantasy")); // genre name with space
        assertTrue(Genre.isValidGenre("Young Adult African American Nonfiction")); // very long genre name
        assertTrue(Genre.isValidGenre("G")); // one character genre name
        assertTrue(Genre.isValidGenre("Suspense ")); // genre name with trailing space
        assertTrue(Genre.isValidGenre("Comic Series 1")); // genre name with digits
    }

    @Test
    public void equals() {
        Genre genre = new Genre(new Name("Young Adult Fantasy"));
        Genre genreCopy = new Genre(new Name("Young Adult Fantasy"));
        Genre genre2 = new Genre(new Name("Young Adult African American Nonfiction"));

        // null -> returns false
        assertFalse(genre.equals(null));

        // different types -> returns false
        assertFalse(genre.equals(0.5f));
        assertFalse(genre.equals(" "));

        // same object -> returns true
        assertTrue(genre.equals(genre));

        // same values -> returns true
        assertTrue(genre.equals(genreCopy));

        // different values -> returns false
        assertFalse(genre.equals(genre2));
    }

    @Test
    public void hashcode() {
        Genre genre = new Genre(new Name("Young Adult Fantasy"));
        Genre genreCopy = new Genre(new Name("Young Adult Fantasy"));
        Genre genre2 = new Genre(new Name("Young Adult African American Nonfiction"));

        // same object -> returns same hashcode
        assertEquals(genre.hashCode(), genre.hashCode());

        // same values -> returns same hashcode
        assertEquals(genre.hashCode(), genreCopy.hashCode());

        // different values -> returns different hashcode
        assertNotEquals(genre.hashCode(), genre2.hashCode());
    }

}
