package seedu.address.model.person.passenger;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.PassengerBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriceContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        Double firstPredicatePrice = 2.34;
        Double secondPredicatePrice = 10.50;

        PriceContainsKeywordsPredicate firstPredicate = new PriceContainsKeywordsPredicate(firstPredicatePrice);
        PriceContainsKeywordsPredicate secondPredicate = new PriceContainsKeywordsPredicate(secondPredicatePrice);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PriceContainsKeywordsPredicate firstPredicateCopy = new PriceContainsKeywordsPredicate(firstPredicatePrice);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different passenger -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_PassengerHasPrice() {
        // Passenger price equals to Predicate
        PriceContainsKeywordsPredicate predicate = new PriceContainsKeywordsPredicate(Double.parseDouble("2.34"));
        assertTrue(predicate.test(new PassengerBuilder().withPrice(2.34).build()));

        // Passenger price bigger than Predicate
        predicate = new PriceContainsKeywordsPredicate(Double.parseDouble("2.34"));
        assertTrue(predicate.test(new PassengerBuilder().withPrice(2.68).build()));

        // Passenger price smaller than Predicate
        predicate = new PriceContainsKeywordsPredicate(Double.parseDouble("2.34"));
        assertFalse(predicate.test(new PassengerBuilder().withPrice(2.0).build()));
    }

    @Test
    public void test_PassengerWithoutPrice_returnsFalse() {
        PriceContainsKeywordsPredicate predicate = new PriceContainsKeywordsPredicate(Double.parseDouble("2.34"));
        assertFalse(predicate.test(new PassengerBuilder().build()));
    }
}
