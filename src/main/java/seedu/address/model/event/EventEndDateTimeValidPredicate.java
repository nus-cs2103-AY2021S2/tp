package seedu.address.model.event;

import java.time.LocalDateTime;
import java.util.function.BiPredicate;

import seedu.address.model.common.Date;

/**
 * Tests that an {@code Event}'s {@code StartDate} and {@code StartTime} is before {@code EndDate} and {@code EndTime}
 */
public class EventEndDateTimeValidPredicate implements BiPredicate<Date, Time> {
    private final Date startDate;
    private final Time startTime;

    /**
     * Constructs an EventEndDateTimeValidPredicate based on given startDate and startTime.
     * @param startDate the given startDate
     * @param startTime the given startTime
     */
    public EventEndDateTimeValidPredicate(Date startDate, Time startTime) {
        this.startDate = startDate;
        this.startTime = startTime;
    }

    @Override
    public boolean test(Date endDate, Time endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(startDate.getDate(), startTime.getTime());
        LocalDateTime endDateTime = LocalDateTime.of(endDate.getDate(), endTime.getTime());
        return startDateTime.isBefore(endDateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventEndDateTimeValidPredicate // instanceof handles nulls
                && startDate.equals(((EventEndDateTimeValidPredicate) other).startDate) // state check
                && startTime.equals(((EventEndDateTimeValidPredicate) other).startTime)); // state check
    }

}
