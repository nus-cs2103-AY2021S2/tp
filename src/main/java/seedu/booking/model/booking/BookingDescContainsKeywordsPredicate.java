package seedu.booking.model.booking;

import java.util.List;
import java.util.function.Predicate;

import seedu.booking.commons.util.StringUtil;

/**
 * Tests that a {@code Booking}'s {@code Description} matches any of the keywords given.
 */
public class BookingDescContainsKeywordsPredicate implements Predicate<Booking> {

    public static final String MESSAGE_CONSTRAINTS = "Keyword(s) for description cannot be empty";

    private final List<String> keywords;

    public BookingDescContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Booking booking) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(booking.getDescription().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingDescContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((BookingDescContainsKeywordsPredicate) other).keywords)); // state check
    }
}
