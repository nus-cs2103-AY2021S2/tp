package seedu.student.model.appointment;

import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code Matriculation Number} matches any of the keywords given.
 */
public class AppointmentContainsKeywordsPredicate implements Predicate<SameDateAppointmentList> {
    private final MatriculationNumber keyword;

    public AppointmentContainsKeywordsPredicate(MatriculationNumber keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(SameDateAppointmentList sameDateAppointmentList) {
        List<Appointment> appointmentList = sameDateAppointmentList.getAppointmentList();

        boolean result = false;

        for(int i=0; i< appointmentList.size(); i++){
            if(keyword.equals(appointmentList.get(i).getMatriculationNumber())){
                result = true;
                break;
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((AppointmentContainsKeywordsPredicate) other).keyword)); // state check
    }

}
