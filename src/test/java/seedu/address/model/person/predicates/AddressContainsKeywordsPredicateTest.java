package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class AddressContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordsList = Collections.singletonList("addressOne");
        List<String> secondPredicateKeywordsList = Arrays.asList("addressTwo", "addressThree");

        AddressContainsKeywordsPredicate firstPredicate =
                new AddressContainsKeywordsPredicate(firstPredicateKeywordsList);
        AddressContainsKeywordsPredicate secondPredicate =
                new AddressContainsKeywordsPredicate(secondPredicateKeywordsList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        AddressContainsKeywordsPredicate firstPredicateCopy =
                new AddressContainsKeywordsPredicate(firstPredicateKeywordsList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);
        assertNotEquals(firstPredicate, new NameContainsKeywordsPredicate(Collections.singletonList("alice")));

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different values -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        AddressContainsKeywordsPredicate predicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("addressOne"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("addressOne addressTwo").build()));

        // Multiple keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("addressOne", "addressTwo"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("addressOne addressTwo").build()));

        // Only one matching keyword
        assertTrue(predicate.test(new PersonBuilder().withAddress("addressOne addressThree").build()));

        // Keywords with non-matching cases
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("aDdREsSonE", "aDDResSTwo"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("addressOne addressTwo").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Non-matching keywords
        AddressContainsKeywordsPredicate predicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("addressOne"));
        assertFalse(predicate.test(new PersonBuilder().withAddress("Git Gud").build()));

        // Keywords matching other fields
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("addressOne", "addressTwo",
                "addressFour@example.com", "phone", "81234567"));
        assertFalse(predicate.test(new PersonBuilder().withName("addressOne").withTags("addressTwo")
                .withAddress("addressThree").withEmail("addressFour@example.com").withModeOfContact("phone")
                .withPhone("81234567").build()));
    }
}
