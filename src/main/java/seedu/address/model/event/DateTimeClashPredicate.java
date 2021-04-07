package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests whether the new {@code Event}'s {@code dateTime} range filtering.
 * This primarily to detect date and time clashes between new and old Appointment and Schedule.
 */
public class DateTimeClashPredicate implements Predicate<Event> {
    private final Event toCheck;
    private final Optional<Event> preEditEvent;

    /**
     * Primary Constructor to constructs {@code DateTimeClashPredicate}
     */
    public DateTimeClashPredicate(Event toCheck) {
        requireNonNull(toCheck);
        this.toCheck = toCheck;
        this.preEditEvent = Optional.empty();
    }

    /**
     * Alternative Constructor that accept a pre-edited {@code Event} for checking purpose.
     */
    public DateTimeClashPredicate(Event toCheck, Event preEditEvent) {
        requireAllNonNull(toCheck, preEditEvent);
        this.toCheck = toCheck;
        this.preEditEvent = Optional.of(preEditEvent);
    }

    @Override
    public boolean test(Event event) {
        if (Objects.equals(toCheck, event)) {
            return false;
        }

        if (preEditEvent.isPresent()) {
            if (Objects.equals(preEditEvent.get(), event)) {
                return false;
            }
        }

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
                || (other instanceof DateTimeClashPredicate // instanceof handles nulls
                && toCheck.equals(((DateTimeClashPredicate) other).toCheck)); // state check
    }
}
