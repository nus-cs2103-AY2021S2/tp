package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.passenger.Passenger;

/**
 * Tests that a {@code Passenger}'s {@code Name} matches any of the keywords given.
 */
public class TripTimeContainsKeywordsPredicate implements Predicate<Passenger> {
    private final List<String> keywords;

    public TripTimeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Passenger passenger) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(passenger.getTripTimeAsStr(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TripTimeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TripTimeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
