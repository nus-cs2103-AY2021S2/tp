package seedu.address.model.event;

import java.util.function.Predicate;

import seedu.address.model.common.Date;

/**
 * Tests that a {@code Event}'s {@code start date} is before or on the by date given.
 */
public class EventEndDateByDatePredicate implements Predicate<Event> {
    private final Date byDate;

    public EventEndDateByDatePredicate(Date byDate) {
        this.byDate = byDate;
    }

    @Override
    public boolean test(Event event) {
        Date eventStartDate = event.getStartDate();
        return eventStartDate.isBefore(byDate) || eventStartDate.isEqual(byDate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventEndDateByDatePredicate)) {
            return false;
        }

        EventEndDateByDatePredicate e = (EventEndDateByDatePredicate) other;
        return byDate.equals(e.byDate); // state check
    }

}
