package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class MatriculationNumberContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeywordList = "A7890123H";
        String secondPredicateKeywordList = "A8901234I";

        MatriculationNumberContainsKeywordsPredicate firstPredicate =
                new MatriculationNumberContainsKeywordsPredicate(firstPredicateKeywordList);
        MatriculationNumberContainsKeywordsPredicate secondPredicate =
                new MatriculationNumberContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MatriculationNumberContainsKeywordsPredicate firstPredicateCopy =
                new MatriculationNumberContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_matriculationNumberContainsKeywords_returnsTrue() {

        // Matching keyword
        MatriculationNumberContainsKeywordsPredicate predicate =
                new MatriculationNumberContainsKeywordsPredicate("A9012345J");
        assertTrue(predicate.test(new StudentBuilder().withName("George Best").withMatric("A9012345J").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {

        // Zero keywords
        MatriculationNumberContainsKeywordsPredicate predicate =
                new MatriculationNumberContainsKeywordsPredicate("");
        assertFalse(predicate.test(new StudentBuilder().withMatric("A0984245T").build()));

        // Non-matching keyword
        predicate = new MatriculationNumberContainsKeywordsPredicate("A0234673E");
        assertFalse(predicate.test(new StudentBuilder().withMatric("A0394852T").build()));

        // Keywords match phone, email and address, but does not match matriculation number
        predicate = new MatriculationNumberContainsKeywordsPredicate("A3458911D  12345 alice@email.com Main Street)");
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withMatric("A3456789D").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
