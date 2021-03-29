package seedu.address.model.event;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.appointment.Appointment;

/**
 * Tests that an {@code Appointment}'s {@code dateTime} matches the date given.
 * This primarily to support the Calendar's Filtering.
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

        boolean isInRange = timeFrom.isAfter(timeToCheck) && timeTo.isBefore(timeFromCheck);
        boolean overlapRange = timeFromCheck.isBefore(timeFrom) && timeToCheck.isAfter(timeTo);
        boolean isExactRange = timeFrom.isEqual(timeFromCheck) || timeTo.isEqual(timeToCheck);
        return isInRange || overlapRange || isExactRange;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateRangePredicate // instanceof handles nulls
                && toCheck.equals(((DateRangePredicate) other).toCheck)); // state check
    }
}
