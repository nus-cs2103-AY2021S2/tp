
package seedu.student.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.student.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class VaccinationStatus {

    public enum VaccinationStatusKeywords {
        VACCINATED, UNVACCINATED
    }

    public static final String MESSAGE_CONSTRAINTS = "Vaccination status should only be of the format 'vaccinated' "
            + "or 'unvaccinated' ";

    public final VaccinationStatusKeywords status; // enum for Vaccination status
    public final String textUI; // string to display on the GUI

    /**
     * Constructs an {@code VaccinationStatus}.
     *
     * @param vaccinationStatus A valid VaccinationStatus.
     */
    public VaccinationStatus(String vaccinationStatus) {
        requireNonNull(vaccinationStatus);
        checkArgument(isValidStatus(vaccinationStatus), MESSAGE_CONSTRAINTS);
        if (vaccinationStatus.toLowerCase().equals("vaccinated")) {
            status = VaccinationStatusKeywords.VACCINATED;
        } else {
            status = VaccinationStatusKeywords.UNVACCINATED;
        }
        textUI = status.toString().toLowerCase();
    }

    /**
     * Returns true if the given string is a valid status.
     *
     * @param test the string whose format is to be checked.
     * @return true if test is a valid statis, false otherwise.
     */
    public static boolean isValidStatus(String test) {
        test = test.toUpperCase();
        try {
            boolean result = VaccinationStatusKeywords.valueOf(test) == VaccinationStatusKeywords.UNVACCINATED
                    || VaccinationStatusKeywords.valueOf(test) == VaccinationStatusKeywords.VACCINATED;
            return result;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String getStringVaccinationStatus() {
        return String.join(", ", getVaccinationStatus());
    }

    public static List<String> getVaccinationStatus() {
        String[] residenceArray = Stream.of(VaccinationStatusKeywords.values())
                .map(VaccinationStatusKeywords::name).toArray(String[]::new);
        return Arrays.asList(residenceArray);
    }

    @Override
    public String toString() {
        return status.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VaccinationStatus // instanceof handles nulls
                && status == ((VaccinationStatus) other).status); // state check
    }

}
