package fooddiary.model.entry;

import static fooddiary.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReviewTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Review(null));
    }

    @Test
    public void constructor_invalidReview_throwsIllegalArgumentException() {
        String invalidReview = "";
        assertThrows(IllegalArgumentException.class, () -> new Review(invalidReview));
    }

    @Test
    public void isValidReview() {
        // null Review
        assertThrows(NullPointerException.class, () -> Review.isValidReview(null));

        // blank Review
        assertFalse(Review.isValidReview("")); // empty string
        assertFalse(Review.isValidReview(" ")); // spaces only

        // valid Review
        assertTrue(Review.isValidReview("PeterJack_1190@example.com"));
        assertTrue(Review.isValidReview("good")); // minimal
        assertTrue(Review.isValidReview("test@localhost")); // alphabets only
        assertTrue(Review.isValidReview("!#$%&'*+/=?`{|}~^.-@.")); // special characters
        assertTrue(Review.isValidReview("123145")); // numeric
        assertTrue(Review.isValidReview("a1+be!@")); // mixture of alphanumeric and special characters
    }
}
