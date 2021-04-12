package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class RemarkContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RemarkContainsKeywordsPredicate firstPredicate =
                new RemarkContainsKeywordsPredicate(firstPredicateKeywordList);
        RemarkContainsKeywordsPredicate secondPredicate =
                new RemarkContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        RemarkContainsKeywordsPredicate firstPredicateCopy =
                new RemarkContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_remarkContainsKeywords_returnsTrue() {
        // One keyword
        RemarkContainsKeywordsPredicate predicate =
                new RemarkContainsKeywordsPredicate(Collections.singletonList("External"));
        assertTrue(predicate.test(new PersonBuilder().withRemark("External Contractor").build()));

        // Multiple keywords
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("External", "Contractor"));
        assertTrue(predicate.test(new PersonBuilder().withRemark("External Contractor").build()));

        // Only one matching keyword
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("Contractor", "Servicing"));
        assertTrue(predicate.test(new PersonBuilder().withRemark("External Servicing").build()));

        // Mixed-case keywords
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("eXtErnal", "CoNtRacTor"));
        assertTrue(predicate.test(new PersonBuilder().withRemark("External Contractor").build()));

        // Similar remark keywords
        predicate = new RemarkContainsKeywordsPredicate(Collections.singletonList("Contract"));
        assertTrue(predicate.test(new PersonBuilder().withRemark("Contractor").build()));
        assertTrue(predicate.test(new PersonBuilder().withRemark("Contractual").build()));
    }

    @Test
    public void test_remarkDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RemarkContainsKeywordsPredicate predicate = new RemarkContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withRemark("External").build()));

        // Non-matching keyword
        predicate = new RemarkContainsKeywordsPredicate(Collections.singletonList("Servicing"));
        assertFalse(predicate.test(new PersonBuilder().withRemark("External Contractor").build()));

        // Keywords match phone, email, name and address, but does not match remark
        predicate = new RemarkContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Alice", "Gordon"));
        assertFalse(predicate.test(new PersonBuilder().withRemark("External Contractor").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withName("Alice Gordon").build()));
    }
}
