package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s {@code Remarks} matches the remark given.
 */
public class AppointmentRemarksPredicate implements Predicate<Appointment> {
    private final String keywords;

    public AppointmentRemarksPredicate(String keywords) throws IllegalArgumentException {
        this.keywords = keywords.toLowerCase();
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getRemarks().remark.toLowerCase().contains(this.keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentRemarksPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentRemarksPredicate) other).keywords)); // state check
    }

}
