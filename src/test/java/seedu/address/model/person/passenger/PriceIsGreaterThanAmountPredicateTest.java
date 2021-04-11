package seedu.address.model.person.passenger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PassengerBuilder;

public class PriceIsGreaterThanAmountPredicateTest {

    @Test
    public void equals() {
        Double firstPredicatePrice = VALID_PRICE_AMY;
        Double secondPredicatePrice = VALID_PRICE_BOB;

        PriceIsGreaterThanAmountPredicate firstPredicate = new PriceIsGreaterThanAmountPredicate(firstPredicatePrice);
        PriceIsGreaterThanAmountPredicate secondPredicate = new PriceIsGreaterThanAmountPredicate(secondPredicatePrice);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PriceIsGreaterThanAmountPredicate firstPredicateCopy =
                new PriceIsGreaterThanAmountPredicate(firstPredicatePrice);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different passenger -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_passengerHasPrice() {
        // Passenger price equals to Predicate
        PriceIsGreaterThanAmountPredicate predicate = new PriceIsGreaterThanAmountPredicate(VALID_PRICE_AMY);
        assertTrue(predicate.test(new PassengerBuilder().withPrice(VALID_PRICE_AMY).build()));

        // Passenger price bigger than Predicate
        predicate = new PriceIsGreaterThanAmountPredicate(VALID_PRICE_AMY);
        assertTrue(predicate.test(new PassengerBuilder().withPrice(VALID_PRICE_BOB).build()));

        // Passenger price smaller than Predicate
        predicate = new PriceIsGreaterThanAmountPredicate(VALID_PRICE_BOB);
        assertFalse(predicate.test(new PassengerBuilder().withPrice(VALID_PRICE_AMY).build()));
    }

    @Test
    public void test_passengerWithoutPrice_returnsFalse() {
        PriceIsGreaterThanAmountPredicate predicate = new PriceIsGreaterThanAmountPredicate(VALID_PRICE_AMY);
        assertFalse(predicate.test(new PassengerBuilder().build()));
    }
}
