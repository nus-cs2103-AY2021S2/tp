package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s {@code Time} matches the time given.
 */
public class AppointmentTimePredicate implements Predicate<Appointment> {
    private final Time time;

    public AppointmentTimePredicate(Time time) {
        this.time = time;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getTime().equals(time);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentTimePredicate // instanceof handles nulls
                && time.equals(((AppointmentTimePredicate) other).time)); // state check
    }

}
