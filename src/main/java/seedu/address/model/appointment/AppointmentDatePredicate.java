package seedu.address.model.appointment;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s {@code Date} matches the date given.
 */
public class AppointmentDatePredicate implements Predicate<Appointment> {
    private final LocalDate date;

    public AppointmentDatePredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getDate().date.equals(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentDatePredicate // instanceof handles nulls
                && date.equals(((AppointmentDatePredicate) other).date)); // state check
    }

}
