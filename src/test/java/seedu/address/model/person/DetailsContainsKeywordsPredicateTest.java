package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class DetailsContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DetailsContainsKeywordsPredicate firstPredicate =
                new DetailsContainsKeywordsPredicate(firstPredicateKeywordList);
        DetailsContainsKeywordsPredicate secondPredicate =
                new DetailsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DetailsContainsKeywordsPredicate firstPredicateCopy =
                new DetailsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_detailsContainsKeywords_returnsTrue() {
        // One keyword
        DetailsContainsKeywordsPredicate predicate =
                new DetailsContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("Alice", "98765432", "president"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("98765432")
                .withRole("president").build()));

        // Only one matching keyword
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("Bob", "tim@example.com", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("aLIce", "mEmbER", "aLiCE@test.com"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withRole("member")
                .withEmail("alice@test.com").build()));
    }

    @Test
    public void test_detailsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("98765432", "John",
                "tim@test.com", "secretary"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withPhone("95632154")
                .withEmail("alice@test.com").withRole("member").build()));
    }
}
