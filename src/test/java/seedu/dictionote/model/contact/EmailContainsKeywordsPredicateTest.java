package seedu.dictionote.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.dictionote.testutil.ContactBuilder;

/**
 * Represents a test class for {@code EmailContainsKeywordsPredicate}
 * <p>
 * This test class is largely derived from {@code NameContainsKeywordsPredicateTest}.
 */
public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        // Note that email search keywords are not confined to a particular format or structure.
        // (i.e., keywords may not be domain names)
        List<String> firstPredicateKeywordList = Collections.singletonList("@email.com");
        List<String> secondPredicateKeywordList = Arrays.asList("hotmail.com", "@outlook.");

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy =
                new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new ContactBuilder().withEmail("alice@example.com").build()));

        // One keyword
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("@example.com"));
        assertTrue(predicate.test(new ContactBuilder().withEmail("alice@example.com").build()));

        // Multiple keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("alice", "example"));
        assertTrue(predicate.test(new ContactBuilder().withEmail("alice@example.com").build()));

        // Only one matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("example.com", "example.net"));
        assertTrue(predicate.test(new ContactBuilder().withEmail("alice@example.com").build()));

        // Mixed-case keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("eXamPLe", "LiCE"));
        assertTrue(predicate.test(new ContactBuilder().withEmail("alice@example.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Arrays.asList("@outlook."));
        assertFalse(predicate.test(new ContactBuilder().withEmail("alice@example.com").build()));

        // Keywords match name, phone and address, but does not match email.
        // For this assertion, note that the email address is different from the one in the previous assertion.
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "Main", "Street"));
        assertFalse(predicate.test(new ContactBuilder().withName("Alice").withPhone("12345")
                .withEmail("apsidalbutton52@example.com").withAddress("Main Street").build()));
    }
}
