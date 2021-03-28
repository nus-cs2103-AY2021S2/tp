package seedu.student.model.appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.student.model.student.MatriculationNumber;

/**
 * Tests that a {@code Appointment}'s {@code Matriculation Number} matches any of the keywords given.
 */
public class AppointmentContainsMatriculationNumberPredicate implements Predicate<SameDateAppointmentList> {

    private final MatriculationNumber keyword;

    public AppointmentContainsMatriculationNumberPredicate(MatriculationNumber keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(SameDateAppointmentList sameDateAppointmentList) {
        List<Appointment> appointmentList = sameDateAppointmentList.getAppointmentList();
        List<Appointment> otherAppointments = new ArrayList<Appointment>();
        int appointmentListSize = appointmentList.size();

        boolean result = false;

        for (int i = 0; i < appointmentListSize; i++) {
            Appointment currentAppointment = appointmentList.get(i);
            if (keyword.equals(currentAppointment.getMatriculationNumber())) {
                result = true;
                break;
            } else {
                appointmentList.remove(currentAppointment);
                otherAppointments.add(currentAppointment);
            }
        }

        for (int k = 0; k < otherAppointments.size(); k++) {
            appointmentList.add(otherAppointments.get(k));
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsMatriculationNumberPredicate // instanceof handles nulls
                && keyword.equals(((AppointmentContainsMatriculationNumberPredicate) other).keyword)); // state check
    }

}
