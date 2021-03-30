package seedu.address.model.event;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that an {@code Event}'s {@code dateTime} range filtering.
 * This primarily to detect date and time clashes between new and old Appointment and Schedule.
 */
public class DateRangePredicate implements Predicate<Event> {
    private final Event toCheck;

    public DateRangePredicate(Event toCheck) {
        this.toCheck = toCheck;
    }

    @Override
    public boolean test(Event event) {
        LocalDateTime timeFromCheck = toCheck.getTimeFrom().getValue();
        LocalDateTime timeToCheck = toCheck.getTimeTo().getValue();

        LocalDateTime timeFrom = event.getTimeFrom().value;
        LocalDateTime timeTo = event.getTimeTo().value;

        boolean hasOverlapRange = (timeFromCheck.isBefore(timeTo)) && (timeToCheck.isAfter(timeFrom));
        boolean isExactRange = timeFrom.isEqual(timeFromCheck) || timeTo.isEqual(timeToCheck);
        return hasOverlapRange || isExactRange;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateRangePredicate // instanceof handles nulls
                && toCheck.equals(((DateRangePredicate) other).toCheck)); // state check
    }
}
