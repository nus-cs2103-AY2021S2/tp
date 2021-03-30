package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s {@code dateTime} matches the date given.
 * This primarily to support the Calendar's Filtering.
 */
public class DateViewPredicate implements Predicate<Appointment> {
    private final AppointmentDateTime date;

    public DateViewPredicate(AppointmentDateTime date) {
        this.date = date;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getTimeFrom().equalsDateCheck(date.value)
                || appointment.getTimeTo().equalsDateCheck(date.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateViewPredicate // instanceof handles nulls
                && date.equals(((DateViewPredicate) other).date)); // state check
    }

    @Override
    public String toString() {
        return date.toDateString();
    }
}
