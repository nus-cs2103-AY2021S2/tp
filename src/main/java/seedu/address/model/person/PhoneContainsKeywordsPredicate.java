package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.passenger.Passenger;

/**
 * Tests that a {@code Passenger}'s {@code Name} matches any of the keywords given.
 */
public class PhoneContainsKeywordsPredicate implements Predicate<Passenger> {
    private final List<String> keywords;

    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Passenger passenger) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(passenger.getPhone().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PhoneContainsKeywordsPredicate) other).keywords)); // state check
    }

}
