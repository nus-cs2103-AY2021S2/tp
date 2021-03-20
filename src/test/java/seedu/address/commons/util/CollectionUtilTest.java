package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class CollectionUtilTest {
    @Test
    public void requireAllNonNullVarargs() {
        // no arguments
        assertNullPointerExceptionNotThrown();

        // any non-empty argument list
        assertNullPointerExceptionNotThrown(new Object(), new Object());
        assertNullPointerExceptionNotThrown("test");
        assertNullPointerExceptionNotThrown("");

        // argument lists with just one null at the beginning
        assertNullPointerExceptionThrown((Object) null);
        assertNullPointerExceptionThrown(null, "", new Object());
        assertNullPointerExceptionThrown(null, new Object(), new Object());

        // argument lists with nulls in the middle
        assertNullPointerExceptionThrown(new Object(), null, null, "test");
        assertNullPointerExceptionThrown("", null, new Object());

        // argument lists with one null as the last argument
        assertNullPointerExceptionThrown("", new Object(), null);
        assertNullPointerExceptionThrown(new Object(), new Object(), null);

        // null reference
        assertNullPointerExceptionThrown((Object[]) null);

        // confirms nulls inside lists in the argument list are not considered
        List<Object> containingNull = Arrays.asList((Object) null);
        assertNullPointerExceptionNotThrown(containingNull, new Object());
    }

    @Test
    public void requireAllNonNullCollection() {
        // lists containing nulls in the front
        assertNullPointerExceptionThrown(Arrays.asList((Object) null));
        assertNullPointerExceptionThrown(Arrays.asList(null, new Object(), ""));

        // lists containing nulls in the middle
        assertNullPointerExceptionThrown(Arrays.asList("spam", null, new Object()));
        assertNullPointerExceptionThrown(Arrays.asList("spam", null, "eggs", null, new Object()));

        // lists containing nulls at the end
        assertNullPointerExceptionThrown(Arrays.asList("spam", new Object(), null));
        assertNullPointerExceptionThrown(Arrays.asList(new Object(), null));

        // null reference
        assertNullPointerExceptionThrown((Collection<Object>) null);

        // empty list
        assertNullPointerExceptionNotThrown(Collections.emptyList());

        // list with all non-null elements
        assertNullPointerExceptionNotThrown(Arrays.asList(new Object(), "ham", Integer.valueOf(1)));
        assertNullPointerExceptionNotThrown(Arrays.asList(new Object()));

        // confirms nulls inside nested lists are not considered
        List<Object> containingNull = Arrays.asList((Object) null);
        assertNullPointerExceptionNotThrown(Arrays.asList(containingNull, new Object()));
    }

    @Test
    public void isAnyNonNull() {
        assertFalse(CollectionUtil.isAnyNonNull());
        assertFalse(CollectionUtil.isAnyNonNull((Object) null));
        assertFalse(CollectionUtil.isAnyNonNull((Object[]) null));
        assertTrue(CollectionUtil.isAnyNonNull(new Object()));
        assertTrue(CollectionUtil.isAnyNonNull(new Object(), null));
    }

    /**
     * Asserts that {@code CollectionUtil#requireAllNonNull(Object...)} throw {@code NullPointerException}
     * if {@code objects} or any element of {@code objects} is null.
     */
    private void assertNullPointerExceptionThrown(Object... objects) {
        assertThrows(NullPointerException.class, () -> requireAllNonNull(objects));
    }

    /**
     * Asserts that {@code CollectionUtil#requireAllNonNull(Collection<?>)} throw {@code NullPointerException}
     * if {@code collection} or any element of {@code collection} is null.
     */
    private void assertNullPointerExceptionThrown(Collection<?> collection) {
        assertThrows(NullPointerException.class, () -> requireAllNonNull(collection));
    }

    private void assertNullPointerExceptionNotThrown(Object... objects) {
        requireAllNonNull(objects);
    }

    private void assertNullPointerExceptionNotThrown(Collection<?> collection) {
        requireAllNonNull(collection);
    }

    //---------------- Tests for tagContainsPartWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for tags: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void tagContainsPartWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("typical"), new Tag("sentence"))), null));
    }

    @Test
    public void tagContainsPartWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", () ->
                CollectionUtil.tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                .asList(new Tag("typical"), new Tag("sentence"))), "  "));
    }

    @Test
    public void tagContainsPartWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", () ->
                CollectionUtil.tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                .asList(new Tag("typical"), new Tag("sentence"))), "aaa BBB"));
    }

    @Test
    public void tagContainsPartWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CollectionUtil
                .tagContainsPartWordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for tags:
     *   - one tag
     *   - multiple tags
     *
     * Possible scenarios returning true:
     *   - matches first tag
     *   - last tag in tags
     *   - middle tag in tags
     *   - matches multiple tags
     *   - query word matches part of a tag word
     *
     * Possible scenarios returning false:
     *   - tag word matches part of the query word
     *   - query word not in tags
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void tagContainsPartWordIgnoreCase_validInputs_correctResult() {
        // Query word not in tags
        assertFalse(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bbb"), new Tag("ccc"))), "eee"));

        // Matches a partial word only
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bbb"),
                                new Tag("ccc"))), "bb")); // Sentence word bigger than query word
        assertFalse(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bbb"),
                                new Tag("ccc"))), "bbbb")); // Query word bigger than sentence word

        // Matches word in the tags, different upper/lower case letters
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccc"))), "aAa")); // First word (boundary case)
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccc"))), "A"));
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccc"))), "Aa"));
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccc"))), "Bbb"));
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccc"))), "B"));
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccc"))), "bB"));
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccC"))), "CCc")); // Last word (boundary case)
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccC"))), "C"));
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccC"))), "CC"));
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("Aaa"))), "aaa")); // Only one word in sentence (boundary case)
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("Aaa"))), "aa"));
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("Aaa"))), "a"));
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bbb"),
                                new Tag("ccc"))), "  ccc  ")); // Leading/trailing spaces
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bbb"),
                                new Tag("ccc"))), "  Cc  "));
        assertTrue(CollectionUtil
                .tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bbb"),
                                new Tag("ccc"))), "  C  "));

        // Matches multiple words in tags
        assertTrue(CollectionUtil.tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                .asList(new Tag("AAA"), new Tag("bBb"),
                        new Tag("ccc"), new Tag("bbb"))), "bbB"));
        assertTrue(CollectionUtil.tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                .asList(new Tag("AAA"), new Tag("bBb"),
                        new Tag("ccc"), new Tag("bbb"))), "bB"));
        assertTrue(CollectionUtil.tagContainsPartWordIgnoreCase(new HashSet<Tag>(Arrays
                .asList(new Tag("AAA"), new Tag("bBb"),
                        new Tag("ccc"), new Tag("bbb"))), "B"));
    }

    //---------------- Tests for tagContainsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for tags: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void tagContainsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CollectionUtil
                .tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("typical"), new Tag("sentence"))), null));
    }

    @Test
    public void tagContainsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", () ->
                CollectionUtil.tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("typical"), new Tag("sentence"))), "  "));
    }

    @Test
    public void tagContainsWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", () ->
                CollectionUtil.tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("typical"), new Tag("sentence"))), "aaa BBB"));
    }

    @Test
    public void tagContainsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CollectionUtil
                .tagContainsWordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for tags:
     *   - one tag
     *   - multiple tags
     *
     * Possible scenarios returning true:
     *   - matches first tag
     *   - last tag in tags
     *   - middle tag in tags
     *   - matches multiple tags
     *
     * Possible scenarios returning false:
     *   - query word matches part of a tag word
     *   - tag word matches part of the query word
     *   - query word not in tags
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void tagContainsWordIgnoreCase_validInputs_correctResult() {
        // Query word not in tags
        assertFalse(CollectionUtil
                .tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bbb"), new Tag("ccc"))), "eee"));

        // Matches a partial word only
        assertFalse(CollectionUtil
                .tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bbb"),
                                new Tag("ccc"))), "bb")); // Sentence word bigger than query word
        assertFalse(CollectionUtil
                .tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bbb"),
                                new Tag("ccc"))), "bbbb")); // Query word bigger than sentence word

        // Matches word in the tags, different upper/lower case letters
        assertTrue(CollectionUtil
                .tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccc"))), "aAa")); // First word (boundary case)
        assertTrue(CollectionUtil
                .tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccc"))), "Bbb"));
        assertTrue(CollectionUtil
                .tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bBb"),
                                new Tag("ccC"))), "CCc")); // Last word (boundary case)
        assertTrue(CollectionUtil
                .tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("Aaa"))), "aaa")); // Only one word in sentence (boundary case)
        assertTrue(CollectionUtil
                .tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                        .asList(new Tag("aaa"), new Tag("bbb"),
                                new Tag("ccc"))), "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in tags
        assertTrue(CollectionUtil.tagContainsWordIgnoreCase(new HashSet<Tag>(Arrays
                .asList(new Tag("AAA"), new Tag("bBb"),
                        new Tag("ccc"), new Tag("bbb"))), "bbB"));
    }
}
