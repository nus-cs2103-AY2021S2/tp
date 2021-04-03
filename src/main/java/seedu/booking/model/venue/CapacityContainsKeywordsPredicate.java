package seedu.booking.model.venue;

import java.util.function.Predicate;

/**
 * Tests that a {@code Venue}'s {@code Capacity} matches the given capacity.
 */
public class CapacityContainsKeywordsPredicate implements Predicate<Venue> {
    private final Integer keyword;

    public static final String MESSAGE_CONSTRAINTS = "Capacity cannot be empty.";

    public CapacityContainsKeywordsPredicate(Integer keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Venue venue) {
        return keyword <= venue.getCapacity().venueCapacity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CapacityContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((CapacityContainsKeywordsPredicate) other).keyword)); // state check
    }

}