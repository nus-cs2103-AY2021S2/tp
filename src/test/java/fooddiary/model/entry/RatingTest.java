package fooddiary.model.entry;

import static fooddiary.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidRating = "";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        // null rating
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // invalid ratings
        assertFalse(Rating.isValidRating("")); // empty string
        assertFalse(Rating.isValidRating(" ")); // spaces only
        assertFalse(Rating.isValidRating("91")); // more than 1 number
        assertFalse(Rating.isValidRating("rating")); // non-numeric
        assertFalse(Rating.isValidRating("9011p041")); // alphabets within digits
        assertFalse(Rating.isValidRating("5 ")); // spaces within digits
        assertFalse(Rating.isValidRating("6")); // ratings higher than 5
        assertFalse(Rating.isValidRating("-1")); // ratings lower than 0

        // valid ratings
        assertTrue(Rating.isValidRating("4")); // exactly 1 number
        assertTrue(Rating.isValidRating("5"));
        assertTrue(Rating.isValidRating("0"));
    }
}
