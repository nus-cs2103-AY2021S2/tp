package seedu.address.model.appointment;

import seedu.address.commons.util.StringUtil;

import java.util.function.Predicate;

import java.util.List;

/**
 * Tests that a {@code Appointment}'s {@code Prefix} matches any of the keywords given.
 */
public class AppointmentContainsKeywordsPredicate implements Predicate<Appointment> {
    private final List<String> patientList;
    private final List<String> doctorList;

    /**
     * Constructor for Predicate
     *
     * @param patientList of keywords of patient names
     * @param doctorList of keywords of doctor names
     */
    public AppointmentContainsKeywordsPredicate(List<String> patientList, List<String> doctorList) {
        this.patientList = patientList;
        this.doctorList = doctorList;
    }

    @Override
    public boolean test(Appointment appointment) {

        Predicate<String> isMatchPatient = keyword -> StringUtil.containsWordIgnoreCase(appointment
                .getPatient().getName().fullName, keyword);

        Predicate<String> isMatchDoctor = keyword -> StringUtil.containsWordIgnoreCase(appointment
                .getDoctor(), keyword);

        // // Find matching timeslots
        // Predicate<String> isMatchTimeslot = keyword
        // -> StringUtil.containsWordIgnoreCase(appointment.getPatient().getName().fullName, keyword));

        // Stream<Tag> tagsStream = appointment.getTags().stream();
        // Predicate<String> isMatchTags = keyword
        //        -> StringUtil.containsWordIgnoreCase(tagsStream.forEach(getTagName()), keyword);

        return patientList.stream().anyMatch(isMatchPatient)
                || doctorList.stream().anyMatch(isMatchDoctor);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.appointment.AppointmentContainsKeywordsPredicate
                && patientList.equals(((AppointmentContainsKeywordsPredicate) other).patientList)
                && doctorList.equals(((AppointmentContainsKeywordsPredicate) other).doctorList));

    }

}

