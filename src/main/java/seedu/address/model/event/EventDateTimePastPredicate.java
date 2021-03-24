package seedu.address.model.event;

import java.time.LocalDateTime;
import java.util.function.BiPredicate;

import seedu.address.model.common.Date;

/**
 * Tests that an {@code Event}'s {@code Name} matches any of the keywords given.
 */
public class EventDateTimePastPredicate implements BiPredicate<Date, Time> {
    private final LocalDateTime nowDateTime = LocalDateTime.now();

    public EventDateTimePastPredicate() { }

    @Override
    public boolean test(Date date, Time time) {
        return nowDateTime.isBefore(
                LocalDateTime.of(date.getDate(), time.getTime()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventDateTimePastPredicate // instanceof handles nulls
                && nowDateTime.equals(((EventDateTimePastPredicate) other).nowDateTime)); // state check
    }
}
