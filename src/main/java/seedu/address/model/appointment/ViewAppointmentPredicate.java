package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 * Since the {@code Name} is tagged to the Person object, the Person object needs to be retrieve first.
 * Then, {@code Email} will be extracted out to perform the Predicate Search.
 */
public class ViewAppointmentPredicate implements Predicate<Appointment> {
    private final Appointment appointment;

    public ViewAppointmentPredicate(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public boolean test(Appointment anotherAppointment) {
        return appointment.equals(anotherAppointment);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewAppointmentPredicate // instanceof handles nulls
                && appointment.equals(((ViewAppointmentPredicate) other).appointment)); // state check
    }
}
