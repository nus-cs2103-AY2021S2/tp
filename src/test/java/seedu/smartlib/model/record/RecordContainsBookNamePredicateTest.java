package seedu.smartlib.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.TypicalModels.RECORD_C;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RecordContainsBookNamePredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RecordContainsBookNamePredicate firstPredicate = new RecordContainsBookNamePredicate(
                firstPredicateKeywordList
        );
        RecordContainsBookNamePredicate secondPredicate = new RecordContainsBookNamePredicate(
                secondPredicateKeywordList
        );

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));
        assertFalse(firstPredicate.equals(" "));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RecordContainsBookNamePredicate firstPredicateCopy = new RecordContainsBookNamePredicate(
                firstPredicateKeywordList
        );
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different book name -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        RecordContainsBookNamePredicate predicate = new RecordContainsBookNamePredicate(
                Collections.singletonList("Secret")
        );
        assertTrue(predicate.test(RECORD_C));

        // Multiple keywords
        predicate = new RecordContainsBookNamePredicate(Arrays.asList("Secret", "The"));
        assertTrue(predicate.test(RECORD_C));

        // Only one matching keyword
        predicate = new RecordContainsBookNamePredicate(Arrays.asList("The", "Republic"));
        assertTrue(predicate.test(RECORD_C));

        // Mixed-case keywords
        predicate = new RecordContainsBookNamePredicate(Arrays.asList("sEcReT", "rePUBliC"));
        assertTrue(predicate.test(RECORD_C));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RecordContainsBookNamePredicate predicate = new RecordContainsBookNamePredicate(Collections.emptyList());
        assertFalse(predicate.test(RECORD_C));

        // Non-matching keyword
        predicate = new RecordContainsBookNamePredicate(Collections.singletonList("Republic"));
        assertFalse(predicate.test(RECORD_C));

        // Keywords match barcode, borrower name, and borrow date, but does not match name
        predicate = new RecordContainsBookNamePredicate(
                Arrays.asList(
                        RECORD_C.getBookBarcode().toString(),
                        "Benson", "Meier",
                        RECORD_C.getDateBorrowed().toString()
                )
        );
        assertFalse(predicate.test(RECORD_C));
    }

}
