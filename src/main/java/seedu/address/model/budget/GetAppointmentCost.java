package seedu.address.model.budget;

import java.util.HashMap;
import java.util.Map;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Name;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.SubjectRate;


/**
 * Class to evaluate cost of ONE appointment.
 */
public class GetAppointmentCost {

    public static final String ADDRESS_BOOK_PATH = "data/addressbook.json";

    private double appointmentCost;

    /**
     * Primary constructor.
     * @param appointment Appointment to evaluate.
     */
    public GetAppointmentCost(Appointment appointment) {

        Name name = appointment.getName();
        SubjectName subjectName = appointment.getSubject();

        // Find tutor and his appointment
        NameSubjectRate nameSubjectRate = new NameSubjectRate(ADDRESS_BOOK_PATH);
        Map<Name, HashMap<SubjectName, SubjectRate>> nameMap =
                nameSubjectRate.getNameRateHash();

        try {
            HashMap<SubjectName, SubjectRate> nameRateMap = nameMap.get(name);
            SubjectRate subjectRate = nameRateMap.get(subjectName);
            this.appointmentCost =
                    subjectRate.rate / (double) 60 * appointment.getDifferenceInMinutes();
        } catch (ClassCastException | NullPointerException e) {
            this.appointmentCost = 0;
        }
    }

    /**
     * @return Cost of appointment {@code Integer}
     */
    public int getAppointmentCost() {
        return (int) appointmentCost;
    }

}
