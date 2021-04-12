package seedu.address.model.pool;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY_LOWER_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CASSANDRA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_AMY_LOWER_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_BOB_LOWER_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_FRIDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_STR_FRIDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_STR_MONDAY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.TripDayContainsKeywordsPredicate;
import seedu.address.testutil.PassengerBuilder;

/*
Keywords changed to lowercase as parser will change all keywords to lowercase for the Predicate.
 */
public class TripDayContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList(VALID_TRIPDAY_STR_MONDAY);
        List<String> secondPredicateKeywordList = Arrays.asList(VALID_TRIPDAY_STR_MONDAY, VALID_TRIPDAY_STR_FRIDAY);

        TripDayContainsKeywordsPredicate firstPredicate = new TripDayContainsKeywordsPredicate(firstPredicateKeywordList);
        TripDayContainsKeywordsPredicate secondPredicate = new TripDayContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TripDayContainsKeywordsPredicate firstPredicateCopy = new TripDayContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different days -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tripDayContainsKeywords_returnsTrue() {
        // One keyword
        TripDayContainsKeywordsPredicate predicate =
                new TripDayContainsKeywordsPredicate(Collections.singletonList(VALID_TRIPDAY_BOB_LOWER_CASE));
        assertTrue(predicate.test(new PassengerBuilder().withTripDay(VALID_TRIPDAY_BOB).build()));

        // Multiple keyword
        predicate = new TripDayContainsKeywordsPredicate(Arrays.asList(VALID_TRIPDAY_BOB_LOWER_CASE,
                        VALID_TRIPDAY_AMY_LOWER_CASE));
        assertTrue(predicate.test(new PassengerBuilder().withTripDay(VALID_TRIPDAY_BOB).build()));
        assertTrue(predicate.test(new PassengerBuilder().withTripDay(VALID_TRIPDAY_AMY).build()));
    }

    @Test
    public void test_tripDayDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TripDayContainsKeywordsPredicate predicate = new TripDayContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PassengerBuilder().withTripDay(VALID_TRIPDAY_BOB).build()));

        // Non-matching keyword
        predicate = new TripDayContainsKeywordsPredicate(Arrays.asList(VALID_TRIPDAY_STR_MONDAY));
        assertFalse(predicate.test(new PassengerBuilder().withTripDay(VALID_TRIPDAY_BOB).build()));

        // Same word but person's name is the day
        predicate = new TripDayContainsKeywordsPredicate(Arrays.asList(VALID_TRIPDAY_STR_MONDAY));
        assertFalse(predicate.test(new PassengerBuilder().withName(VALID_NAME_CASSANDRA)
                .withTripDay(VALID_TRIPDAY_FRIDAY).build()));
    }
}
