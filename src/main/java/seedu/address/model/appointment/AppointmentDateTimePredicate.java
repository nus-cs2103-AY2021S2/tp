package seedu.address.model.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s {@code Date} matches the date given and {@code Time} matches the time given.
 * A pair of date and time uniquely identifies an appointment.
 */
public class AppointmentDateTimePredicate implements Predicate<Appointment> {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructs an {@code AppointmentDateTimePredicate}.
     *
     * @param date The date to be matched against.
     * @param time The time to be matched against.
     */
    public AppointmentDateTimePredicate(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getDate().date.equals(date) && appointment.getTime().time.equals(time);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppointmentDateTimePredicate)) {
            return false;
        }
        AppointmentDateTimePredicate otherPredicate = (AppointmentDateTimePredicate) other;
        return date.equals(otherPredicate.date) && time.equals(otherPredicate.time);
    }

}
