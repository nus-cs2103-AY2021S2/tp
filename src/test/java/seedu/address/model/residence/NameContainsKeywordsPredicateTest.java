package seedu.address.model.residence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.BookingBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        seedu.address.model.person.NameContainsKeywordsPredicate firstPredicate =
                new seedu.address.model.person.NameContainsKeywordsPredicate(firstPredicateKeywordList);
        seedu.address.model.person.NameContainsKeywordsPredicate secondPredicate =
                new seedu.address.model.person.NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        seedu.address.model.person.NameContainsKeywordsPredicate firstPredicateCopy =
                new seedu.address.model.person.NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        seedu.address.model.person.NameContainsKeywordsPredicate predicate =
                new seedu.address.model.person.NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new BookingBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new seedu.address.model.person.NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new BookingBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new seedu.address.model.person.NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new BookingBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new seedu.address.model.person.NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new BookingBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        LocalDate start = LocalDate.of(2021, 3, 22);
        LocalDate end = LocalDate.of(2021, 3, 25);

        // Zero keywords
        seedu.address.model.person.NameContainsKeywordsPredicate predicate =
                new seedu.address.model.person.NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new BookingBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new seedu.address.model.person.NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new BookingBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345"));
        assertFalse(predicate.test(new BookingBuilder().withName("Alice").withPhone("12345")
                .withStart(start).withEnd(end).build()));
    }
}
