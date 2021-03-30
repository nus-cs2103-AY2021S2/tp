package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroUnsignedInteger("")); // Boundary value
        assertFalse(StringUtil.isNonZeroUnsignedInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonZeroUnsignedInteger("a"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroUnsignedInteger("-1"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroUnsignedInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonZeroUnsignedInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroUnsignedInteger("1")); // Boundary value
        assertTrue(StringUtil.isNonZeroUnsignedInteger("10"));
    }


    //---------------- Tests for sentenceContainsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void sentenceContainsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil
                .sentenceContainsWordIgnoreCase("typical sentence", null));
    }

    @Test
    public void sentenceContainsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.sentenceContainsWordIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void sentenceContainsWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", ()
            -> StringUtil.sentenceContainsWordIgnoreCase("typical sentence", "aaa BBB"));
    }

    @Test
    public void sentenceContainsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil
                .sentenceContainsWordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *
     * Possible scenarios returning false:
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *   - query word not in sentence
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void sentenceContainsWordIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.sentenceContainsWordIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.sentenceContainsWordIgnoreCase("    ", "123"));

        // Query word not in sentence
        assertFalse(StringUtil
                .sentenceContainsWordIgnoreCase("aaa bbb ccc", "eee"));
        assertFalse(StringUtil
                .sentenceContainsWordIgnoreCase("aaa bbb ccc", "testing"));

        // Matches a partial word only
        assertFalse(StringUtil
                .sentenceContainsWordIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger than query word
        assertFalse(StringUtil
                .sentenceContainsWordIgnoreCase("aaa bbb ccc", "bbbb")); // Query word bigger than sentence word

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil
                .sentenceContainsWordIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil
                .sentenceContainsWordIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil
                .sentenceContainsWordIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil
                .sentenceContainsWordIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil
                .sentenceContainsWordIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.sentenceContainsWordIgnoreCase("AAA bBb ccc  bbb", "bbB"));
    }

    //---------------- Tests for sentenceContainsPartWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void sentenceContainsPartWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil
                .sentenceContainsPartWordIgnoreCase("typical sentence", null));
    }

    @Test
    public void sentenceContainsPartWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", () ->
                StringUtil.sentenceContainsPartWordIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void sentenceContainsPartWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", () ->
                StringUtil.sentenceContainsPartWordIgnoreCase("typical sentence", "aaa BBB"));
    }

    @Test
    public void sentenceContainsPartWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil
                .sentenceContainsPartWordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *   - sentence word bigger than query word
     *
     * Possible scenarios returning false:
     *   - query word bigger than sentence word
     *   - query word not in sentence
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void sentenceContainsPartWordIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.sentenceContainsPartWordIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.sentenceContainsPartWordIgnoreCase("    ", "123"));

        // Query word not in sentence
        assertFalse(StringUtil
                .sentenceContainsPartWordIgnoreCase("aaa bbb ccc", "ee"));
        assertFalse(StringUtil
                .sentenceContainsPartWordIgnoreCase("aaa bbb ccc", "testing"));

        // Matches a partial word
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("aaa bbb ccc", "a"));
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("aaa bbb ccc", "b"));
        // Sentence word bigger than query word
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("aaa bbb ccc", "bb"));
        // Query word bigger than sentence word
        assertFalse(StringUtil
                .sentenceContainsPartWordIgnoreCase("aaa bbb ccc", "bbbb"));

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("aaa bBb ccc@1", "Cc@1"));
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("  AAA   bBb   ccc  ", "a"));
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("Aaa", "a"));
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces
        assertTrue(StringUtil
                .sentenceContainsPartWordIgnoreCase("aaa bbb ccc", "  c  "));

        // Matches multiple words in sentence
        assertTrue(StringUtil.sentenceContainsPartWordIgnoreCase("AAA bBb ccc  bbb", "bbB"));
        assertTrue(StringUtil.sentenceContainsPartWordIgnoreCase("AAA bBb ccc  bbb", "bB"));
    }

    //---------------- Tests for getDetails --------------------------------------

    /*
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void getDetails_exceptionGiven() {
        assertTrue(StringUtil.getDetails(new FileNotFoundException("file not found"))
            .contains("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getDetails(null));
    }

}
