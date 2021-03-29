package seedu.student.model.appointment;

import java.util.function.Predicate;

import seedu.student.model.student.MatriculationNumber;

/**
 * Tests that a {@code SameDateAppointmentList} contains an {@code Appointment} whose {@code MatriculationNumber}
 * matches any of the keywords given.
 */
public class AppointmentListContainsMatriculationNumberPredicate implements Predicate<SameDateAppointmentList> {
    private final MatriculationNumber keyword;

    public AppointmentListContainsMatriculationNumberPredicate(MatriculationNumber keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(SameDateAppointmentList appointmentList) {
        return appointmentList.containsMatricNumber(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentListContainsMatriculationNumberPredicate // instanceof handles nulls
                && keyword.equals((
                        (AppointmentListContainsMatriculationNumberPredicate) other).keyword)); // state check
    }

}
