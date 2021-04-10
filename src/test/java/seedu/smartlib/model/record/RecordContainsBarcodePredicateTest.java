package seedu.smartlib.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.TypicalModels.RECORD_A;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.book.Barcode;

public class RecordContainsBarcodePredicateTest {

    @Test
    public void equals() {
        RecordContainsBarcodePredicate firstPredicate = new RecordContainsBarcodePredicate(
                new Barcode(Barcode.MAX_VALUE)
        );
        RecordContainsBarcodePredicate secondPredicate = new RecordContainsBarcodePredicate(
                new Barcode(Barcode.MIN_VALUE)
        );

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));
        assertFalse(firstPredicate.equals(" "));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RecordContainsBarcodePredicate firstPredicateCopy = new RecordContainsBarcodePredicate(
                new Barcode(Barcode.MAX_VALUE)
        );
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different barcode -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test() {
        // EP: same barcode -> true
        RecordContainsBarcodePredicate predicate = new RecordContainsBarcodePredicate(
                new Barcode(Barcode.MAX_VALUE)
        );
        assertTrue(predicate.test(RECORD_A));

        // EP: different barcode -> false
        predicate = new RecordContainsBarcodePredicate(
                new Barcode(Barcode.MIN_VALUE)
        );
        assertFalse(predicate.test(RECORD_A));
    }

}
