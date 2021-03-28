package seedu.address.model.pool;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.passenger.Passenger;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Passenger}'s {@code Name} matches any of the keywords given.
 */
public class PassengerContainsKeywordsPredicate implements Predicate<Pool> {
    private final List<String> keywords;

    public PassengerContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Pool pool) {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PassengerContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PassengerContainsKeywordsPredicate) other).keywords)); // state check
    }

}
