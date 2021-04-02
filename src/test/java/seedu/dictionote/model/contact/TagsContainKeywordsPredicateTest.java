package seedu.dictionote.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.dictionote.testutil.ContactBuilder;

/**
 * Represents a test class for {@code TagsContainKeywordsPredicateTest}.
 * <p>
 * This test class is largely derived from {@code NameContainsKeywordsPredicateTest}.
 */
public class TagsContainKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("friends");
        List<String> secondPredicateKeywordList = Arrays.asList("professors", "tutors");

        TagsContainKeywordsPredicate firstPredicate = new TagsContainKeywordsPredicate(firstPredicateKeywordList);
        TagsContainKeywordsPredicate secondPredicate = new TagsContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainKeywordsPredicate firstPredicateCopy = new TagsContainKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        // Zero keywords
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new ContactBuilder().withTags("friends").build()));

        // One keyword
        predicate = new TagsContainKeywordsPredicate(Collections.singletonList("tutors"));
        assertTrue(predicate.test(new ContactBuilder().withTags("professors", "tutors").build()));

        // Multiple keywords
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("professors", "tutors"));
        assertTrue(predicate.test(new ContactBuilder().withTags("professors", "tutors").build()));

        // Mixed-case keywords
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("FaMIly", "siBlInGS"));
        assertTrue(predicate.test(new ContactBuilder().withTags("family", "siblings").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(Arrays.asList("professors"));
        assertFalse(predicate.test(new ContactBuilder().withTags("friends", "owesMoney").build()));

        // Only one matching keyword
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("friends", "professors"));
        assertFalse(predicate.test(new ContactBuilder().withTags("friends").build()));

        // Keywords match name, phone, email and address, but do not match tags.
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new ContactBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("friends").build()));
    }
}
