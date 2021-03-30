package seedu.address.model.appointment;

import java.util.function.Predicate;

import seedu.address.model.remark.Remark;

/**
 * Tests that an {@code Appointment}'s {@code Remarks} matches the remark given.
 */
public class AppointmentRemarksPredicate implements Predicate<Appointment> {
    private final Remark keywords;

    public AppointmentRemarksPredicate(String keywords) throws IllegalArgumentException {
        this.keywords = new Remark(keywords);
    }

    @Override
    public boolean test(Appointment appointment) {
        return this.keywords.equals(appointment.getRemarks());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentRemarksPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentRemarksPredicate) other).keywords)); // state check
    }

}
