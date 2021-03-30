package seedu.address.model.event;

import java.util.function.Predicate;

import seedu.address.model.common.Date;

/**
 * Tests that a {@code Event}'s {@code start date} is before or on the by date given
 * and its {@code end date} is later or on the by date given.
 */
public class EventFindSchedulePredicate implements Predicate<Event> {
    private final Date byDate;

    public EventFindSchedulePredicate(Date byDate) {
        this.byDate = byDate;
    }

    @Override
    public boolean test(Event event) {
        return isStartDateBeforeOrOnByDate(event) && isEndDateLaterOrOnByDate(event);
    }

    private boolean isStartDateBeforeOrOnByDate(Event event) {
        Date eventStartDate = event.getStartDate();
        return eventStartDate.isBefore(byDate) || eventStartDate.isEqual(byDate);
    }

    private boolean isEndDateLaterOrOnByDate(Event event) {
        Date eventEndDate = event.getEndDate();
        return eventEndDate.isAfter(byDate) || eventEndDate.isEqual(byDate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventFindSchedulePredicate)) {
            return false;
        }

        EventFindSchedulePredicate e = (EventFindSchedulePredicate) other;
        return byDate.equals(e.byDate); // state check
    }

}
