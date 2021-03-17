package seedu.address.model.appointment;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Appointment}'s {@code Prefix} matches any of the keywords given.
 */
public class AppointmentContainsKeywordsPredicate implements Predicate<Appointment> {
    private final List<String> patientList;
    private final List<String> doctorList;
    private final List<String> timeStartList;
    private final List<String> tagsList;

    /**
     * Constructor for Predicate
     *
     * @param patientList of keywords of patient names
     * @param doctorList of keywords of doctor names
     */
    public AppointmentContainsKeywordsPredicate(List<String> patientList, List<String> doctorList,
                                                List<String> timeStartList, List<String> tagsList) {
        this.patientList = patientList;
        this.doctorList = doctorList;
        this.tagsList = tagsList;
        this.timeStartList = timeStartList;
    }

    @Override
    public boolean test(Appointment appointment) {

        Predicate<String> isMatchPatient = keyword -> StringUtil.containsWordIgnoreCase(appointment
                .getPatient().getName().fullName, keyword);

        Predicate<String> isMatchDoctor = keyword -> StringUtil.containsWordIgnoreCase(appointment
                .getDoctor(), keyword);

        System.out.println(appointment.getAppointmentStart().toString());
        Predicate<String> isMatchTimeStart = keyword
            -> StringUtil.containsWordIgnoreCase(appointment.getAppointmentStart().toString()
                .replace("T", " "), keyword);

        Set<String> stringSet = appointment.convertStringSet(appointment.getTags());
        String allTags = String.join(" ", stringSet);
        Predicate<String> isMatchTags = keyword -> StringUtil.containsWordIgnoreCase(allTags, keyword);

        return patientList.stream().anyMatch(isMatchPatient)
                || doctorList.stream().anyMatch(isMatchDoctor)
                || timeStartList.stream().anyMatch(isMatchTimeStart)
                || tagsList.stream().anyMatch(isMatchTags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.appointment.AppointmentContainsKeywordsPredicate
                && patientList.equals(((AppointmentContainsKeywordsPredicate) other).patientList)
                && doctorList.equals(((AppointmentContainsKeywordsPredicate) other).doctorList)
                && tagsList.equals(((AppointmentContainsKeywordsPredicate) other).tagsList));
    }

}
