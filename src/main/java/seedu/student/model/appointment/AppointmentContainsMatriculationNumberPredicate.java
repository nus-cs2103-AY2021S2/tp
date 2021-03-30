package seedu.student.model.appointment;

import java.util.function.Predicate;

import seedu.student.model.student.MatriculationNumber;

/**
 * Tests that an {@code Appointment}'s {@code MatriculationNumber} matches any of the keywords given.
 */
public class AppointmentContainsMatriculationNumberPredicate implements Predicate<Appointment> {
    private final MatriculationNumber keyword;

    public AppointmentContainsMatriculationNumberPredicate(MatriculationNumber keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getMatriculationNumber().equals(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsMatriculationNumberPredicate // instanceof handles nulls
                && keyword.equals(((AppointmentContainsMatriculationNumberPredicate) other).keyword)); // state check
    }

}
