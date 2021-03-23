package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class NamePredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NamePredicate firstPredicate = new NamePredicate(firstPredicateKeywordList);
        NamePredicate secondPredicate = new NamePredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NamePredicate firstPredicateCopy = new NamePredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different appointment -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NamePredicate predicate = new NamePredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NamePredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NamePredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NamePredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NamePredicate predicate = new NamePredicate(Collections.emptyList());
        assertFalse(predicate.test(new AppointmentBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NamePredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));

        // Keywords match subject and location, but does not match name
        predicate = new NamePredicate(Arrays.asList("12345", "English", "Main", "Street"));
        assertFalse(predicate.test(new AppointmentBuilder().withName("Alice").withSubject("English")
                .withAddress("Main Street").build()));
    }
}
