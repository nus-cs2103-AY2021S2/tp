package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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

        // missing parts
        assertFalse(Review.isValidReview("@example.com")); // missing local part
        assertFalse(Review.isValidReview("peterjackexample.com")); // missing '@' symbol
        assertFalse(Review.isValidReview("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Review.isValidReview("peterjack@-")); // invalid domain name
        assertFalse(Review.isValidReview("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Review.isValidReview("peter jack@example.com")); // spaces in local part
        assertFalse(Review.isValidReview("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Review.isValidReview(" peterjack@example.com")); // leading space
        assertFalse(Review.isValidReview("peterjack@example.com ")); // trailing space
        assertFalse(Review.isValidReview("peterjack@@example.com")); // double '@' symbol
        assertFalse(Review.isValidReview("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Review.isValidReview("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Review.isValidReview("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Review.isValidReview("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Review.isValidReview("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Review.isValidReview("peterjack@example.com-")); // domain name ends with a hyphen

        // valid Review
        assertTrue(Review.isValidReview("PeterJack_1190@example.com"));
        assertTrue(Review.isValidReview("a@bc")); // minimal
        assertTrue(Review.isValidReview("test@localhost")); // alphabets only
        assertTrue(Review.isValidReview("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(Review.isValidReview("123@145")); // numeric local part and domain name
        assertTrue(Review.isValidReview("a1+be!@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Review.isValidReview("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Review.isValidReview("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
