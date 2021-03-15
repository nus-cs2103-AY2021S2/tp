package seedu.address.model.appointment;

import seedu.address.model.Appointment;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s {@code dateTime} matches the date given.
 * This primarily to support the Calendar's Filtering.
 */
public class DateFindPredicate implements Predicate<Appointment> {
    private final List<LocalDate> dates;

    public DateFindPredicate(List<LocalDate> dates) {
        this.dates = dates;
    }

    @Override
    public boolean test(Appointment appointment) {
        return dates.stream()
                .anyMatch(date -> appointment.getDateTime().toLocalDate().equals(date));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateFindPredicate // instanceof handles nulls
                && dates.equals(((DateFindPredicate) other).dates)); // state check
    }
}
