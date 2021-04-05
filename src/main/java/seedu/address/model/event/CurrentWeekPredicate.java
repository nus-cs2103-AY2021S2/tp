package seedu.address.model.event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

/**
 * Tests that an {@code Event}'s {@code dateTime} matches the date given.
 * This primarily to support the Timetable's filtering.
 */
public class CurrentWeekPredicate implements Predicate<Event> {
    private final LocalDate mondayDate;
    private final LocalDate sundayDate;

    /**
     * Constructs a predicate based on the queried date to compute the date on Monday and Sunday.
     * @param date
     */
    public CurrentWeekPredicate(LocalDate date) {
        this.mondayDate = date.with(DayOfWeek.MONDAY);
        this.sundayDate = date.with(DayOfWeek.SUNDAY);
    }

    @Override
    public boolean test(Event event) {
        LocalDate eventDate = event.getTimeFrom().value.toLocalDate();
        return !eventDate.isBefore(mondayDate) && !eventDate.isAfter(sundayDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CurrentWeekPredicate // instanceof handles nulls
                && mondayDate.isEqual(((CurrentWeekPredicate) other).mondayDate)
                && sundayDate.isEqual(((CurrentWeekPredicate) other).sundayDate)); // state check
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return mondayDate.format(format) + " to " + sundayDate.format(format);
    }
}
