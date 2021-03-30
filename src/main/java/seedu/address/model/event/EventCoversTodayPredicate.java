package seedu.address.model.event;

import java.time.LocalDate;
import java.util.function.Predicate;

public class EventCoversTodayPredicate implements Predicate<Event> {
    private final LocalDate todayDate = LocalDate.now();

    public EventCoversTodayPredicate() { }

    @Override
    public boolean test(Event event) {
        return (event.getStartDate().getDate().isBefore(todayDate) || event.getStartDate().getDate().isEqual(todayDate))
                && (todayDate.isBefore(event.getEndDate().getDate())
                || todayDate.isEqual(event.getEndDate().getDate()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventCoversTodayPredicate // instanceof handles nulls
                && todayDate.equals(((EventCoversTodayPredicate) other).todayDate)); // state check
    }
}
